package com.example.Error_Tracker.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
  name = "issue",
  indexes = {
    @Index(name = "idx_issue_project_id", columnList = "project_id"),
    @Index(name = "idx_issue_fingerprint", columnList = "fingerprint"),
    @Index(name = "idx_issue_status", columnList = "status"),
    @Index(name = "idx_issue_created_at", columnList = "created_at"),
    @Index(name = "idx_issue_last_seen", columnList = "last_seen_at")
  }
)
public class Issue {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, length = 500)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String description;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  @Builder.Default
  private IssueStatus status = IssueStatus.UNRESOLVED;

  @Column(nullable = false, unique = true, length = 64)
  private String fingerprint;

  @Column(nullable = false)
  @Builder.Default
  private Long eventCount = 1L;

  @Column(nullable = false)
  @Builder.Default
  private Long userCount = 1L;

  @Column(name = "first_seen_at", nullable = false, updatable = false)
  private LocalDateTime firstSeenAt;

  @Column(name = "last_seen_at", nullable = false)
  private LocalDateTime lastSeenAt;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "assigned_user_id")
  private User assignedUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_issue_id")
  private Issue parentIssue;

  @OneToMany(mappedBy = "parentIssue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Issue> childIssues;

  @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Notification> notifications;

  @PrePersist
  protected void onCreate() {
    firstSeenAt = LocalDateTime.now();
    lastSeenAt = LocalDateTime.now();
    createdAt = LocalDateTime.now();
    updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    updatedAt = LocalDateTime.now();
  }

  public enum IssueStatus {
    UNRESOLVED,
    RESOLVED,
    IGNORED,
    REGRESSED
  }
}
