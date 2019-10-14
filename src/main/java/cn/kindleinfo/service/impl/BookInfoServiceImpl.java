package cn.kindleinfo.service.impl;

import cn.kindleinfo.api.entity.BookInfo;
import cn.kindleinfo.api.service.BookInfoService;
import cn.kindleinfo.reptile.dao.BookInfoDao;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;

/**
 * @Author tongning
 * @Date 2019/8/12 0012
 * function:<
 * <p>
 * >
 */
@Service
public class BookInfoServiceImpl implements BookInfoService {

    @Autowired
    private BookInfoDao bookInfoDao;


    @Override
    public PageInfo getBook(int pageNum, int pageSize, BookInfo bookInfo) {
        WeekendSqls<BookInfo> sqls = WeekendSqls.custom();

        if (bookInfo.getBookName() != null) {
            sqls.andLike(BookInfo::getBookName, "%" + bookInfo.getBookName() + "%");
        }

        if (bookInfo.getBookCode() != null) {
            sqls.andEqualTo(BookInfo::getBookCode, bookInfo.getBookCode());
        }
        Example example = Example.builder(BookInfo.class).andWhere(sqls).build();
        PageHelper.startPage(pageNum, pageSize);
        Page page = (Page) bookInfoDao.selectByExample(example);
        PageInfo pageInfo = new PageInfo(page);
        return pageInfo;
    }
}
