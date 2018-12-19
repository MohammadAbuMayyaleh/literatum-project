package com.atypon.literatumproject.webadmintool.model;


import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Table(name = "article")
public class Article {

    @Id
    @Column(name = "article_doi" , length = 100)
    private String articleDoi;

    @Column(name = "article_title")
    private String articleTitle;

    @Column(name = "first_page")
    private String firstPage;

    @Column(name = "last_page")
    private String lastPage;

    @Column(name = "subject")
    private String subject;

    @Column(name = "article_path")
    private String articlePath;

    @ManyToOne
    @JoinColumn(name = "issue_doi")
    private Issue parentIssue;

    @ManyToOne
    @JoinColumn(name = "auther_id")
    private ArticleAuthor articleAuthor;

    public String getArticleDoi() {
        return articleDoi;
    }

    public void setArticleDoi(String articleDoi) {
        this.articleDoi = articleDoi;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(String firstPage) {
        this.firstPage = firstPage;
    }

    public String getLastPage() {
        return lastPage;
    }

    public void setLastPage(String lastPage) {
        this.lastPage = lastPage;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getArticlePath() {
        return articlePath;
    }

    public void setArticlePath(String articlePath) {
        this.articlePath = articlePath;
    }

    public ArticleAuthor getArticleAuthor() {
        return articleAuthor;
    }

    public void setArticleAuthor(ArticleAuthor articleAuthor) {
        this.articleAuthor = articleAuthor;
    }

    public Issue getParentIssue() {
        return parentIssue;
    }

    public void setParentIssue(Issue parentIssue) {
        this.parentIssue = parentIssue;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleDoi='" + articleDoi + '\'' +
                ", articleTitle='" + articleTitle + '\'' +
                ", firstPage='" + firstPage + '\'' +
                ", lastPage='" + lastPage + '\'' +
                ", subject='" + subject + '\'' +
                ", articlePath='" + articlePath + '\'' +
                ", parentIssue=" + parentIssue +
                ", articleAuthor=" + articleAuthor +
                '}';
    }
}
