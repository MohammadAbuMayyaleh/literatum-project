package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Article;

import java.util.List;

public interface ArticleService {

    public void createArticle(Article article);

    public Article getArticleByDoi(String doi);

    public List<Article> getAllArticles();

}
