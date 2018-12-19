package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Issue;
import com.atypon.literatumproject.webadmintool.model.Journal;
import com.atypon.literatumproject.webadmintool.services.JournalService;
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
public class ExtractIssueMetaServiceServiceImpl implements ExtractIssueMetaService {

    private final Issue issue;
    private final JournalService journalService;

    @Autowired
    public ExtractIssueMetaServiceServiceImpl(Issue issue, JournalService journalService) {
        this.issue = issue;
        this.journalService = journalService;
    }

    @Override
    public Issue extractIssueFromXml(String xmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String element = startElement.getName().getLocalPart();
                    if ("month".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setMonth(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if (("year".equals(element))) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setYear(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if ("volume".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setVolume(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if ("issue".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setIssueNum(Integer.parseInt(xmlEvent.asCharacters().getData()));
                    } else if ("issue-id".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setIssueDoi(xmlEvent.asCharacters().getData());
                    } else if ("issue-title".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setIssueTitle(xmlEvent.asCharacters().getData());
                    }else if("issn".equals(element)){
                        xmlEvent = xmlEventReader.nextEvent();
                        issue.setParentJournal(journalService.getJournalByDoi(xmlEvent.asCharacters().getData()));
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        }
        return issue;
    }
}
