package com.example.Error_Tracker.repository;

import org.springframework.stereotype.Repository;
import com.example.Error_Tracker.entity.Token;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TokenRepository extends JpaRepository<Token, UUID> {
}
