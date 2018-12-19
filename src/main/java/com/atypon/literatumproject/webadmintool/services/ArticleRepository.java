package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {
}
