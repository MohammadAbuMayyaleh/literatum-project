package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Journal;

import java.util.List;

public interface JournalService {

    public void createJournal(Journal journal);

    public Journal getJournalByDoi(String doi);

    public List<Journal> getAllJournals();

}
