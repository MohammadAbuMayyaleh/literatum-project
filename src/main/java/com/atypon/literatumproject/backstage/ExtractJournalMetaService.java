package com.atypon.literatumproject.backstage;

import com.atypon.literatumproject.webadmintool.model.Journal;

public interface ExtractJournalMetaService {
    public Journal extractJournalFromXml(String xmlFile);
}
