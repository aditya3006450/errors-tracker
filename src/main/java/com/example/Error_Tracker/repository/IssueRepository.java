package com.example.Error_Tracker.repository;

import org.springframework.stereotype.Repository;
import com.example.Error_Tracker.entity.Issue;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, UUID> {
}
