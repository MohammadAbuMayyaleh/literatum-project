package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Journal;
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
public class ExtractJournalMetaServiceImpl implements ExtractJournalMetaService {

    private final Journal journal;

    @Autowired
    public ExtractJournalMetaServiceImpl(Journal journal) {
        this.journal = journal;
    }

    public Journal extractJournalFromXml(String xmlFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream(xmlFile));
            while (xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();
                    String element = startElement.getName().getLocalPart();
                    if ("journal-title".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        journal.setJournalTitle(xmlEvent.asCharacters().getData());
                    } else if (("issn".equals(element))) {
                        xmlEvent = xmlEventReader.nextEvent();
                        journal.setJournalIssn(xmlEvent.asCharacters().getData());
                    } else if ("publisher-name".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        journal.setPublisherName(xmlEvent.asCharacters().getData());
                    } else if ("publisher-loc".equals(element)) {
                        xmlEvent = xmlEventReader.nextEvent();
                        journal.setPublisherLocation(xmlEvent.asCharacters().getData());
                    }
                }
            }

        } catch (FileNotFoundException |
                XMLStreamException e) {
            e.printStackTrace();
        }
        return journal;
    }
}
