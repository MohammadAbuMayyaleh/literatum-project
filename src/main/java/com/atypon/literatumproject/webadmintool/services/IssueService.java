package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Issue;

import java.util.List;

public interface IssueService {

    public void createIssue(Issue issue);

    public Issue getIssueByDoi(String doi);

    public List<Issue> getAllIssues();

}
