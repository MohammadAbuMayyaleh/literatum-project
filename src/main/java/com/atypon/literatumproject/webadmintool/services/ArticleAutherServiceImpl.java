package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.ArticleAuthor;
import com.atypon.literatumproject.webadmintool.repository.ArticleAutherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleAutherServiceImpl implements ArticleAutherService {

    private final ArticleAutherRepository articleAutherRepository;

    @Autowired
    public ArticleAutherServiceImpl(ArticleAutherRepository articleAutherRepository) {
        this.articleAutherRepository = articleAutherRepository;
    }

    @Override
    public void addAuther(ArticleAuthor articleAuthor) {
        articleAutherRepository.save(articleAuthor);
    }

    @Override
    public ArticleAuthor getAutherById(Long AutherId) {
        return articleAutherRepository.getOne(AutherId);
    }

    @Override
    public List<ArticleAuthor> getAllAuthors() {
        return articleAutherRepository.findAll();
    }
}
