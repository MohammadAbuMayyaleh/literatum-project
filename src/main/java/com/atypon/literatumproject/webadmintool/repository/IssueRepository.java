package com.atypon.literatumproject.webadmintool.repository;

import com.atypon.literatumproject.webadmintool.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue,String> {
}
