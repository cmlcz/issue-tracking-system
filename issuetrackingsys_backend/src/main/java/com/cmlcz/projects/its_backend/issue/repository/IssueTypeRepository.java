package com.cmlcz.projects.its_backend.issue.repository;

import com.cmlcz.projects.its_backend.issue.model.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueTypeRepository extends JpaRepository<IssueType, Long> {
    boolean existsByNameIgnoreCase(String name);

    List<IssueType> getAllIssueTypesByOrderByCreationDateAsc();
}
