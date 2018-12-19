package com.atypon.literatumproject.webadmintool.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Component
@Table(name = "journal")
public class Journal {

    @Id
    @Column(name = "journal_issn" , length = 100)
    private String journalIssn;

    @Column(name = "journal_title",nullable = false)
    private String journalTitle;

    @Column(name = "publisher_name",nullable = false)
    private String publisherName;

    @Column(name = "publisher_location",nullable = false)
    private String publisherLocation;

    @OneToMany(mappedBy = "parentJournal")
    private Set<Issue> issues = new HashSet<>();

    public Journal() {
    }

    public Journal(String journalIssn, String journalTitle, String publisherName, String publisherLocation) {
        this.journalIssn = journalIssn;
        this.journalTitle = journalTitle;
        this.publisherName = publisherName;
        this.publisherLocation = publisherLocation;
    }

    public String getJournalIssn() {
        return journalIssn;
    }

    public void setJournalIssn(String journalIssn) {
        this.journalIssn = journalIssn;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getPublisherLocation() {
        return publisherLocation;
    }

    public void setPublisherLocation(String publisherLocation) {
        this.publisherLocation = publisherLocation;
    }

    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "journalIssn='" + journalIssn + '\'' +
                ", journalTitle='" + journalTitle + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", publisherLocation='" + publisherLocation + '\'' +
                ", issues=" + issues +
                '}';
    }
}
