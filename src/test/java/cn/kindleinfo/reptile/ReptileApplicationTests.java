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
import java.util.IdentityHashMap;
import java.util.Map;

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

    public void test(){
        Map<Integer,String> map = new IdentityHashMap<>();
        map.put(1, "Hello");
        map.putIfAbsent(1, "World");
        print(map.get(1));
        print(map.size());

        map.put(1024, "A");
        map.putIfAbsent(1024, "B");
        print(map.get(1024));
        print(map.size());

    }

    private static void print(Object object) {
        System.out.print(object + " ");
    }
}
