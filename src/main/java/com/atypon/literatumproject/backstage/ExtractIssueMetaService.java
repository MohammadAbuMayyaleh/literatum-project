package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Issue;

public interface ExtractIssueMetaService {
    public Issue extractIssueFromXml(String xmlFile);
}
