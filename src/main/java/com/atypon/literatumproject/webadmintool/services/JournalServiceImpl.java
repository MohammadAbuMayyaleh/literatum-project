package com.atypon.literatumproject.webadmintool.services;

import com.atypon.literatumproject.webadmintool.model.Journal;
import com.atypon.literatumproject.webadmintool.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public void createJournal(Journal journal) {
        journalRepository.save(journal);
    }

    @Override
    public Journal getJournalByDoi(String doi) {
        return journalRepository.getOne(doi);
    }

    @Override
    public List<Journal> getAllJournals() {
        return journalRepository.findAll();
    }
}
