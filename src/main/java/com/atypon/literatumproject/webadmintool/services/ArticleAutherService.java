package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.ArticleAuthor;

import java.util.List;

public interface ArticleAutherService {

    public void addAuther(ArticleAuthor articleAuthor);

    public ArticleAuthor getAutherById(Long AutherId);

    public List<ArticleAuthor> getAllAuthors();

}
