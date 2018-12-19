package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Article;

public interface ExtractArticleMetaService {

    public Article extractArticleFromXml(String xmlFile,String issueXmlFile);
}
