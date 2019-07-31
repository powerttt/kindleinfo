package cn.kindleinfo.reptile.site;

import cn.kindleinfo.reptile.invoker.ReptileInvokerService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import org.jsoup.select.Elements;
import java.io.IOException;

/**
 * @Author tongning
 * @Date 2019/7/31 0031
 * function:<
 * <p>
 * >
 */
@Service("ireadweek")
public class IReadWeekInvoker implements ReptileInvokerService {

    /**
     * 爬取数据
     */
    @Override
    public void start() {
        StringBuilder url = new StringBuilder().append("http://www.ireadweek.com/index.php?m=article&a=index&id=");
        for (int i = 1000; i < 1050; i++) {
            resolvePage(url.append(i).toString());
        }


    }

    private void resolvePage(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla").get();//模拟火狐浏览器
        } catch (IOException e) {
            e.printStackTrace();
        }
        //这里根据在网页中分析的类选择器来获取电影列表所在的节点，hanghang-shu-content-font->唯一
        Elements div = doc.getElementsByClass("hanghang-shu-content-font");
























    }
}
