package com.weekie.service.impl;

import com.weekie.entity.News;
import com.weekie.mapper.NewsMapper;
import com.weekie.mapper.StockMapper;
import com.weekie.service.NewsService;
import com.weekie.vo.NewsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public NewsVO getNews() {
        NewsVO newsVO=new NewsVO();
        newsVO.setNewsList( newsMapper.getAllNews());
        return newsVO;
    }

    @Override
    public NewsVO getNewsById(Long newsId) {
        NewsVO newsVO=new NewsVO();
        News news=newsMapper.getNewsById(newsId);
        List<News>newsList=new ArrayList<>();
        newsList.add(news);
        newsVO.setNewsList( newsList);
        return newsVO;
    }
}
