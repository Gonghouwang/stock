package com.weekie.controller;

import com.weekie.dto.TradeDTO;
import com.weekie.result.Result;
import com.weekie.service.NewsService;
import com.weekie.service.UserService;
import com.weekie.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public Result<NewsVO> getNews() {
        NewsVO newsVO=newsService.getNews();
        return Result.success(newsVO);
    }
    @GetMapping("/{newsId}")
    public Result<NewsVO> getNews(@PathVariable Long newsId) {
        NewsVO newsVO=newsService.getNewsById(newsId);
        return Result.success(newsVO);
    }


}
