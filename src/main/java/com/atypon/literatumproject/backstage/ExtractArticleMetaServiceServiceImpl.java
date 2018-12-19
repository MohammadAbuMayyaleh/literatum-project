package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Article;
import com.atypon.literatumproject.webadmintool.model.ArticleAuthor;
import com.atypon.literatumproject.webadmintool.services.ArticleAutherService;
import com.atypon.literatumproject.webadmintool.services.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Service
public class ExtractArticleMetaServiceServiceImpl implements ExtractArticleMetaService {


    private final Article article;
    private final ArticleAuthor articleAuthor;
    private final ArticleAutherService articleAutherService;
    private final IssueService issueService;

    @Autowired
    public ExtractArticleMetaServiceServiceImpl(Article article, ArticleAuthor articleAuthor, ArticleAutherService articleAutherService, IssueService issueService) {
        this.article = article;
        this.articleAuthor = articleAuthor;
        this.articleAutherService = articleAutherService;
        this.issueService = issueService;
    }

    @Override
    public Article extractArticleFromXml(String articleXmlFile,String issueXmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(articleXmlFile));
            XMLEventReader xmlEventReader2 = xmlInputFactory.createXMLEventReader(new FileInputStream(issueXmlFile));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String element = startElement.getName().getLocalPart();
                    if ("article-id".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        article.setArticleDoi(xmlEvent.asCharacters().getData());
                    } else if (("subject".equals(element))) {
                        xmlEvent = xmlEventReader.nextEvent();
                        article.setSubject(xmlEvent.asCharacters().getData());
                    } else if ("article-title".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        article.setArticleTitle(xmlEvent.asCharacters().getData());
                    } else if ("given-names".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        articleAuthor.setAutherName(xmlEvent.asCharacters().getData());
                    } else if ("surname".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        articleAuthor.setSurName(xmlEvent.asCharacters().getData());
                    } else if ("degrees".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        articleAuthor.setDegrees(xmlEvent.asCharacters().getData());
                    } else if ("fpage".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        article.setFirstPage(xmlEvent.asCharacters().getData());
                    } else if ("lpage".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        article.setLastPage(xmlEvent.asCharacters().getData());
                    }
                }
            }
            while (xmlEventReader2.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader2.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String element = startElement.getName().getLocalPart();
                    if ("issue-id".equals(element)) {
                        xmlEvent = xmlEventReader2.nextEvent();
                        article.setParentIssue(issueService.getIssueByDoi(xmlEvent.asCharacters().getData()));
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        articleAutherService.addAuther(articleAuthor);
        article.setArticleAuthor(articleAuthor);
        return article;
    }
}