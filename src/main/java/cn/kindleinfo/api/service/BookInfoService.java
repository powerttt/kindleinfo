package cn.kindleinfo.api.service;

import cn.kindleinfo.api.entity.BookInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BookInfoService {

    PageInfo getBook(int pageNum, int pageSize,BookInfo bookInfo);

}
