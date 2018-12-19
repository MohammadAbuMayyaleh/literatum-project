package com.atypon.literatumproject.backstage;

import org.springframework.stereotype.Service;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Objects;

@Service
public class SearchFilesServiceImpl implements SearchFileService {

    @Override
    public String findUploadedFileName(Part uploadedFile) {
        String contentDispositionHeader = uploadedFile.getHeader("content-disposition");
        String[] elements = contentDispositionHeader.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1)
                        .trim().replace("\"", "");
            }
        }
        return null;
    }


    @Override
    public String findIssueXmlPath(String inputFile) {
        File file = new File(inputFile);
        for (File directory : Objects.requireNonNull(file.listFiles())) {
            if (directory.getName().equals("issue-files")) {
                File[] xmlFiles = directory.listFiles((folder, name) -> name.endsWith(".xml"));
                return Objects.requireNonNull(xmlFiles)[0].getAbsolutePath();
            }
        }
        return null;
    }

    @Override
    public String findArticleXmlPath(String inputFile) {
        File file = new File(inputFile);
        for (File directory : Objects.requireNonNull(file.listFiles())) {
            if (!directory.getName().equals("issue-files")) {
                File[] xmlFiles = directory.listFiles((folder, name) -> name.endsWith(".xml"));
                return Objects.requireNonNull(xmlFiles)[0].getAbsolutePath();
            }
        }
        return null;
    }

    @Override
    public String findUnZipFilePath(String outputPath, String zipFileName) {
        File directory = new File(outputPath);
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (zipFileName.contains(file.getName())) {
                return file.getAbsolutePath();
            }
        }
        return null;
    }
}
