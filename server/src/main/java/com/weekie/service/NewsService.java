package com.weekie.service;

import com.weekie.vo.NewsVO;

public interface NewsService {
    NewsVO getNews();

    NewsVO getNewsById(Long newsId);
}
