package com.atypon.literatumproject.backstage;

import org.springframework.stereotype.Service;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

@Service
public interface XslTransformationService {
    public void transformToHtml(String xslFilePath,String outputFilePath,String xmlFilePath);
}
