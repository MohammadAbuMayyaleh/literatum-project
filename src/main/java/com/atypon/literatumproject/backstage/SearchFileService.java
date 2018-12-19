package com.atypon.literatumproject.backstage;

import javax.servlet.http.Part;

public interface SearchFileService {

    public String findUploadedFileName(Part uploadedFile);

    public String findIssueXmlPath(String inputFile);

    public String findArticleXmlPath(String inputFile);

    public String findUnZipFilePath(String outputPath, String zipFileName);
}
