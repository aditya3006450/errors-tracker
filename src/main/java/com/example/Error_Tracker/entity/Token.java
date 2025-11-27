package com.example.Error_Tracker.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(
  name = "token",
  indexes = {
    @Index(name = "idx_token_value", columnList = "token_value", unique = true),
    @Index(name = "idx_token_project_id", columnList = "project_id"),
    @Index(name = "idx_token_created_at", columnList = "created_at")
  }
)
public class Token {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true, length = 255)
  private String tokenValue;

  @Column(length = 255)
  private String name;

  @Column(nullable = false)
  @Builder.Default
  private Boolean isActive = true;

  @Column(name = "last_used_at")
  private LocalDateTime lastUsedAt;

  @Column(name = "expires_at")
  private LocalDateTime expiresAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }
}
