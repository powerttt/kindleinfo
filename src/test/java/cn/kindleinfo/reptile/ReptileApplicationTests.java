package cn.kindleinfo.reptile;

import cn.kindleinfo.api.entity.BookInfo;
import cn.kindleinfo.reptile.dao.BookInfoDao;
import cn.kindleinfo.reptile.invoker.ReptileInvokerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReptileApplicationTests {

    @Autowired
    private ReptileInvokerService reptileInvokerService;

    @Autowired
    private BookInfoDao bookInfoDao;

    @Test
    public void contextLoads() {
     reptileInvokerService.start();
    }

    @Test
    public void isnert() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName("aaa");
        bookInfo.setScore(1d);
        bookInfo.setPhotoUrl("1");
        bookInfoDao.insertSelective(bookInfo);
    }
}
