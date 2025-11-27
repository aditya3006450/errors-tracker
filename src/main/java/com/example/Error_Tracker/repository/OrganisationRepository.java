package com.example.Error_Tracker.repository;

import org.springframework.stereotype.Repository;
import com.example.Error_Tracker.entity.Organisation;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, UUID> {
}
