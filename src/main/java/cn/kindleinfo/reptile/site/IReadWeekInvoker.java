package cn.kindleinfo.reptile.site;

import cn.kindleinfo.api.entity.*;
import cn.kindleinfo.enums.IReadWeekEnum;
import cn.kindleinfo.reptile.dao.AuthorInfoDao;
import cn.kindleinfo.reptile.dao.BookInfoDao;
import cn.kindleinfo.reptile.dao.BookLabelDao;
import cn.kindleinfo.reptile.dao.PhotoDao;
import cn.kindleinfo.reptile.invoker.ReptileInvokerService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Slf4j
@Service("ireadweek")
public class IReadWeekInvoker implements ReptileInvokerService {


    @Autowired
    private BookInfoDao bookInfoDao;

    @Autowired
    private PhotoDao photoDao;

    @Autowired
    private BookLabelDao bookLabelDao;

    @Autowired
    private AuthorInfoDao authorInfoDao;

    @Autowired
    private RedisTemplate redisTemplate;

    private Map<String, BookInfo> bookMap;
    private Map<String, AuthorInfo> authorMap;
    private Map<String, BookLabel> bookLabelMap;

    /**
     * 爬取数据
     */
    @Override
    public void start() {
        StringBuilder url = new StringBuilder().append("http://www.ireadweek.com/index.php?m=article&a=index&id=");

        bookMap = (Map<String, BookInfo>) redisTemplate.opsForHash().get(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_BOOK_MAP);
        if (bookMap == null) {
            bookMap = new HashMap();
        }
        authorMap = (Map<String, AuthorInfo>) redisTemplate.opsForHash().get(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_AUTHOR_MAP);
        if (authorMap == null) {
            authorMap = new HashMap();
        }
        bookLabelMap = (Map<String, BookLabel>) redisTemplate.opsForHash().get(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_LABEL_MAP);
        if (bookLabelMap == null) {
            bookLabelMap = new HashMap();
        }

        int index = 14;
        if (redisTemplate.hasKey(IReadWeekEnum.REDIS_KEY_SITE_INDEX)) {
            index = (int) redisTemplate.opsForValue().get(IReadWeekEnum.REDIS_KEY_SITE_INDEX);
        }

        for (int i = index; i < 14000; i++) {
            try {
                log.info("[ URL : {}{}]", url.toString(), i);
                Document doc = Jsoup.connect(url.toString() + i).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36").get();

                reptile(doc, i);

                redisTemplate.opsForValue().set(IReadWeekEnum.REDIS_KEY_SITE_INDEX, i);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


    private void reptile(Document doc, int i) throws NullPointerException, InterruptedException {
        Elements baseInfo = doc.getElementsByClass("hanghang-shu-content-font");

        BookInfo bookInfo = new BookInfo();
        AuthorInfo authorInfo = new AuthorInfo();
        Photo photo = new Photo();


        Elements b = baseInfo.get(0).getElementsByTag("p");
        // 书名
        bookInfo.setBookName(subToEnd(b.get(IReadWeekEnum.BOOK_NAME_INDEX).text(), "："));
        if (StringUtils.isEmpty(bookInfo.getBookName())) {
            return;
        }
        log.info("[Book Name : {}]", bookInfo.getBookName());
        bookInfo.setBookCode(IReadWeekEnum.CODE_BASE_BOOK + (IReadWeekEnum.CODE_COUNT + bookMap.size() + 1));

        // 作者
        String authorName = subToEnd(b.get(IReadWeekEnum.BOOK_AUTHOR_INDEX).text(), "：");
        // IReadWeekEnum.CODE_BASE_AUTHOR + IReadWeekEnum.CODE_COUNT + authorMap.size();
        if (authorMap.containsKey(authorName)) {
            authorInfo = authorMap.get(authorName);
        } else {
            authorInfo.setAuthorName(subToEnd(b.get(IReadWeekEnum.BOOK_AUTHOR_INDEX).text(), "："));
            authorInfo.setAuthorCode(IReadWeekEnum.CODE_BASE_AUTHOR + (IReadWeekEnum.CODE_COUNT + authorMap.size() + 1));
            authorMap.put(authorName, authorInfo);
            authorInfoDao.insertSelective(authorInfo);
        }
        bookInfo.setAuthorCode(authorInfo.getAuthorCode());

        String labelHtmlText = subToEnd(b.get(IReadWeekEnum.BOOK_LABEL_INDEX).text(), "：");
        if (labelHtmlText.indexOf(" ") != -1) {
            // label
            String[] labelHtmlTexts = labelHtmlText.split(" ");
            for (String label : labelHtmlTexts) {
                BookLabel bookLabel = new BookLabel();
                bookLabel.setLabelName(label);
                bookLabel.setLabelCode(IReadWeekEnum.CODE_BASE_LABEL + (IReadWeekEnum.CODE_COUNT + bookLabelMap.size() + 1));
                bookInfo.setLabelCode(bookLabel.getLabelCode());
                bookLabelMap.put(bookLabel.getLabelName(), bookLabel);
                // 多为两个直接插入数据库
                bookLabelDao.insertSelective(bookLabel);
            }
        } else {
            BookLabel bookLabel = new BookLabel();
            bookLabel.setLabelName(labelHtmlText);
            bookLabel.setLabelCode(IReadWeekEnum.CODE_BASE_LABEL + (IReadWeekEnum.CODE_COUNT + bookLabelMap.size() + 1));
            bookInfo.setLabelCode(bookLabel.getLabelCode());
            bookLabelMap.put(bookLabel.getLabelName(), bookLabel);
            bookLabelDao.insertSelective(bookLabel);
        }
        // 评分
        bookInfo.setScore(Double.valueOf(subToEnd(b.get(IReadWeekEnum.BOOK_SCORE_INDEX).text(), "：")));
        log.info("Score : {}", bookInfo.getScore());

        // 简介
        for (int i1 = b.size() - 1; i1 >= 0; i1--) {
            if (!StringUtils.isEmpty(b.get(i1).text())) {
                bookInfo.setIntroduction(subToEnd(b.get(i1).text(), "："));
                break;
            }
        }


        // 图片
        Elements imgs = doc.getElementsByTag("img");
        String imgSrc = imgs.get(0).attr("abs:src");
        if (imgSrc == null) {
            log.warn("{} 获取图片为空，ID：[{}]", bookInfo.getBookCode(), i);
        } else {
            bookInfo.setPhotoUrl(imgSrc);
            photo.setCode(bookInfo.getBookCode());
            photo.setType(IReadWeekEnum.PHOTO_BOOK_TYPE);
            photo.setPhoto(downImgBase64(imgSrc, bookInfo.getBookCode()));
        }

        bookMap.put(bookInfo.getBookName(), bookInfo);

        // 下载地址
        Elements downsInfo = doc.getElementsByClass("hanghang-shu-content-btn");

        Elements d = downsInfo.get(0).getElementsByTag("a");

        BookDownUrls bookDownUrls = new BookDownUrls();
        if (d.size() > 2) {
            bookDownUrls.setCt(getDownUrls(d, IReadWeekEnum.DOWN_URL_CT_INDEX));
        }
        if (d.size() > 1) {
            bookDownUrls.setTencent(getDownUrls(d, IReadWeekEnum.DOWN_URL_WEI_YUN_INDEX));
        }
        if (d.size() > 0) {
            bookDownUrls.setBaidu(getDownUrls(d, IReadWeekEnum.DOWN_URL_BAI_DU_PAD_INDEX));
        }
        bookInfo.setDownsJson(JSON.toJSONString(bookDownUrls));

        // 插入数据库
        bookInfoDao.insertSelective(bookInfo);
        photoDao.insertSelective(photo);

        // 存入redis，程序中断下次继续

        redisTemplate.opsForHash().put(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_BOOK_MAP, bookMap);
        redisTemplate.opsForHash().put(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_AUTHOR_MAP, authorMap);
        redisTemplate.opsForHash().put(IReadWeekEnum.REDIS_KEY_SITE, IReadWeekEnum.REDIS_KEY_LABEL_MAP, bookLabelMap);

//        Thread.sleep(1);

    }

    private String getDownUrls(Elements d, int index) {
        return d.get(index).attr("href");
    }

    private String subToEnd(String str, String indexOf) {
        return str.substring(str.indexOf(indexOf) + 1, str.length());
    }


    private String downImgBase64(String imgUrl, String bookCode) {
        ByteArrayOutputStream data = new ByteArrayOutputStream();
        try {
            URL url = new URL(imgUrl);
            byte[] by = new byte[1024];

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            InputStream is = conn.getInputStream();
            // 读到内存
            int len = -1;
            while ((len = is.read(by)) != -1) {
                data.write(by, 0, len);
            }
            // 关闭流
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 转码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data.toByteArray());
    }


}
