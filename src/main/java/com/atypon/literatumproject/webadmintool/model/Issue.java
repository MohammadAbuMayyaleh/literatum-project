package com.atypon.literatumproject.webadmintool.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Entity
@Component
@Table(name = "issue")
public class Issue {

    @Id
    @Column(name = "issue_doi", nullable = false, length = 100)
    private String issueDoi;

    @Column(name = "issue_number")
    private int issueNum;

    @Column(name = "volume")
    private int volume;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "issueTitle")
    private String issueTitle;

    @ManyToOne
    @JoinColumn(name = "journal_issn")
    private Journal parentJournal;

    @OneToMany(mappedBy = "parentIssue")
    private Set<Article> articles;

    public Issue() {
    }

    public Issue(String issueDoi, int issueNum, int volume, int year, int month, String issueTitle) {
        this.issueDoi = issueDoi;
        this.issueNum = issueNum;
        this.volume = volume;
        this.year = year;
        this.month = month;
        this.issueTitle = issueTitle;
    }

    public String getIssueDoi() {
        return issueDoi;
    }

    public void setIssueDoi(String issueDoi) {
        this.issueDoi = issueDoi;
    }

    public int getIssueNum() {
        return issueNum;
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public Journal getParentJournal() {
        return parentJournal;
    }

    public void setParentJournal(Journal parentJournal) {
        this.parentJournal = parentJournal;
    }

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }

    public String getIssueTitle() {
        return issueTitle;
    }

    public void setIssueTitle(String issueTitle) {
        this.issueTitle = issueTitle;
    }

    @Override
    public String toString() {
        return "Issue{" +
                "issueDoi='" + issueDoi + '\'' +
                ", issueNum=" + issueNum +
                ", volume=" + volume +
                ", year=" + year +
                ", month=" + month +
                ", issueTitle='" + issueTitle + '\'' +
                ", parentJournal=" + parentJournal +
                ", articles=" + articles +
                '}';
    }
}
