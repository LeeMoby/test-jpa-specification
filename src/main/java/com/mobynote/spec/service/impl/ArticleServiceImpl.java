package com.mobynote.spec.service.impl;

import com.mobynote.spec.entity.Article;
import com.mobynote.spec.entity.Reply;
import com.mobynote.spec.repo.ArticleRepository;
import com.mobynote.spec.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findBy(String title, String replyContent) {
        Specification<Article> articleSpec = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (StringUtils.isNotEmpty(title)) {
                Predicate titlePre = cb.equal(root.get("title"), title);
                predicateList.add(titlePre);
            }

            if (StringUtils.isNotEmpty(replyContent)) {
                Join<Article, Reply> replyJoin = root.join("replies", JoinType.LEFT);
                Predicate replyPre = cb.equal(replyJoin.get("content"), replyContent);
                predicateList.add(replyPre);
            }

            query.orderBy(cb.desc(root.get("publishTime")));

            Predicate[] predicates = new Predicate[predicateList.size()];
            return cb.and(predicateList.toArray(predicates));
        };

        return articleRepository.findAll(articleSpec);
    }
}
