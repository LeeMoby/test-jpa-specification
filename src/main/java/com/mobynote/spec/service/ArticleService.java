package com.mobynote.spec.service;

import com.mobynote.spec.entity.Article;

import java.util.List;

public interface ArticleService {
    public List<Article> findBy(String title, String replyContent);
}
