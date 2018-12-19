package com.atypon.literatumproject.backstage;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.transform.JDOMSource;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class XslTransformationServiceImpl implements XslTransformationService {

    @Override
    public void transformToHtml(String xslFilePath, String outputFilePath, String xmlFilePath) {
        try {
            TransformerFactory transformerFactory = TransformerFactoryImpl.newInstance();
            Source xslt = new StreamSource(new File(xslFilePath));
            Transformer transformer = transformerFactory.newTransformer(xslt);
            File xmlFile = new File(xmlFilePath);
            Source xml = new JDOMSource(getDocument(xmlFile));
            String generatedHtmlFileName = xmlFile.getName().replaceAll(".xml",".html");
            File output = new File(outputFilePath+generatedHtmlFileName);
            transformer.transform(xml, new StreamResult(output));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Document getDocument(File xmlFile) {
        SAXBuilder saxBuilder = new SAXBuilder();
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        Document document = null;
        try {
            document = saxBuilder.build(xmlFile);
            removeDoctypeFromXml(document, xmlFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return document;
    }

    private void removeDoctypeFromXml(Document xmlDoc, File xmlFile) {
        xmlDoc.setDocType(null);
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.setFormat(Format.getPrettyFormat());
        try {
            xmlOutputter.output(xmlDoc, new FileOutputStream(xmlFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
