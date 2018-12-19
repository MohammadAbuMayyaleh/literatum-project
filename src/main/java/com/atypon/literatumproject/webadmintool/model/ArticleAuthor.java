package com.atypon.literatumproject.webadmintool.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
@Table(name = "article_author")
public class ArticleAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "auther_id" , length = 100)
    private Long autherId;

    @Column(name = "auther_name")
    private String autherName;

    @Column(name = "surname")
    private String surName;

    @Column(name = "degrees")
    private String degrees;

    @OneToMany(mappedBy = "articleAuthor")
    private Set<Article> articles;

    public ArticleAuthor() {
    }

    public ArticleAuthor(String autherName, String surName, String degrees) {
        this.autherName = autherName;
        this.surName = surName;
        this.degrees = degrees;
    }

    public Long getAutherId() {
        return autherId;
    }

    public void setAutherId(Long autherId) {
        this.autherId = autherId;
    }

    public String getAutherName() {
        return autherName;
    }

    public void setAutherName(String autherName) {
        this.autherName = autherName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }
}
