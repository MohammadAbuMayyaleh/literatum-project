package com.atypon.literatumproject.webadmintool.controller;

import com.atypon.literatumproject.backstage.*;
import com.atypon.literatumproject.webadmintool.model.Article;
import com.atypon.literatumproject.webadmintool.model.Issue;
import com.atypon.literatumproject.webadmintool.model.Journal;
import com.atypon.literatumproject.webadmintool.services.ArticleService;
import com.atypon.literatumproject.webadmintool.services.IssueService;
import com.atypon.literatumproject.webadmintool.services.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@Controller
public class BackstageController {

    //we can change it from application.properties
    @Value("${literatum.OUTPUT_PATH}")
    private String outputPath;

    @Value("${literatum.INPUT_PATH}")
    private String inputPath;

    @Value("${literatum.XSL_PATH}")
    private String xslFilePath;

    @Value("${literatum.HTMLPAGES_PATH}")
    private String htmlFilePath;

    @Autowired
    private ExtractJournalMetaService journalMetaService;
    @Autowired
    private ExtractIssueMetaService issueMetaService;
    @Autowired
    private ExtractArticleMetaService articleMetaService;
    @Autowired
    private UnZipSubmissionService unZipSubmissionService;
    @Autowired
    private SearchFileService searchFileService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private IssueService issueService;
    @Autowired
    private JournalService journalService;
    @Autowired
    private XslTransformationService xslTransformationService;

    @PostMapping("/backstage/upload/")
    public String uploadFile(HttpServletRequest request) {
        Part uploadedFile = null;
        try {
            uploadedFile = request.getPart("uploadedFile");
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
        String fileUploadedName = searchFileService.findUploadedFileName(uploadedFile);
        unZipSubmissionService.unZipSubmissionFile(inputPath + fileUploadedName, outputPath);
        String unZipFilePath = searchFileService.findUnZipFilePath(outputPath, fileUploadedName);
        String issueXmlFilePath = searchFileService.findIssueXmlPath(unZipFilePath);
        String articleXmlFilePath = searchFileService.findArticleXmlPath(unZipFilePath);
        Journal journal = journalMetaService.extractJournalFromXml(articleXmlFilePath);
        journalService.createJournal(journal);
        Issue issue = issueMetaService.extractIssueFromXml(issueXmlFilePath);
        issueService.createIssue(issue);
        Article article = articleMetaService.extractArticleFromXml(articleXmlFilePath,issueXmlFilePath);
        articleService.createArticle(article);
        xslTransformationService.transformToHtml(xslFilePath, htmlFilePath, articleXmlFilePath);
        return "backstage";
    }
}
