package com.mobynote.spec.service;

import com.mobynote.spec.ApplicationTests;
import com.mobynote.spec.entity.Article;
import com.mobynote.spec.entity.Reply;
import com.mobynote.spec.repo.ArticleRepository;
import com.mobynote.spec.repo.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class ArticleServiceTest extends ApplicationTests {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private ReplyRepository replyRepository;

    @Before
    public void setUp() {
        createArticle();
        createReply();
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }


    @Test
    @Transactional
    public void findBy() {
        List<Article> articles = articleService.findBy("Action in Java", "Great");
        assertThat(articles.size(), equalTo(1));
        assertThat(articles.get(0).getTitle(), equalTo("Action in Java"));
    }

    @Transactional
    void createArticle() {
        LocalDateTime now = LocalDateTime.now();
        List<Article> articles = new ArrayList<>();
        articles.add(Article.builder()
                .title("Action in Java")
                .publishTime(now.minusDays(3L))
                .build());
        articles.add(Article.builder()
                .title("Action in Scala")
                .publishTime(now.minusDays(2L))
                .build());
        articles.add(Article.builder()
                .title("Action in Python")
                .publishTime(now.minusDays(1L))
                .build());
        repository.saveAll(articles);
    }

    @Transactional
    void createReply() {
        List<Article> articles = repository.findAll();
        List<Reply> replies = new ArrayList<>();
        replies.add(Reply.builder()
                .article(articles.get(0))
                .content("Great")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(0))
                .content("Good")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(1))
                .content("Nice")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(1))
                .content("Better")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(2))
                .content("Best")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(2))
                .content("I like this.")
                .visible(true)
                .build());
        replies.add(Reply.builder()
                .article(articles.get(0))
                .content("It's good article.")
                .visible(true)
                .build());

        replyRepository.saveAll(replies);

    }
}