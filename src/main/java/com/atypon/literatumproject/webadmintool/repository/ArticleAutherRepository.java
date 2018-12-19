package com.atypon.literatumproject.webadmintool.repository;


import com.atypon.literatumproject.webadmintool.model.ArticleAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleAutherRepository extends JpaRepository<ArticleAuthor,Long> {
}
