package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void createArticle(Article article) {
        articleRepository.save(article);
    }

    @Override
    public Article getArticleByDoi(String doi) {
        return articleRepository.getOne(doi);
    }

    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
