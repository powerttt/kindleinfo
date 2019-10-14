package cn.kindleinfo.controller;

import cn.kindleinfo.api.entity.BookInfo;
import cn.kindleinfo.api.service.BookInfoService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author tongning
 * @Date 2019/8/12 0012
 * function:<
 * <p>
 * >
 */

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private BookInfoService bookInfoService;

    @GetMapping
    public PageInfo getList(int pageNum, int pageSize, BookInfo bookInfo) {
        return bookInfoService.getBook(pageNum, pageSize, bookInfo);
    }

}
