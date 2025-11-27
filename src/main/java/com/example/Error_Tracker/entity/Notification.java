package com.example.Error_Tracker.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
  name = "notification",
  indexes = {
    @Index(name = "idx_notification_user_id", columnList = "user_id"),
    @Index(name = "idx_notification_issue_id", columnList = "issue_id"),
    @Index(name = "idx_notification_is_read", columnList = "is_read"),
    @Index(name = "idx_notification_created_at", columnList = "created_at")
  }
)
public class Notification {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private NotificationType type;

  @Column(columnDefinition = "TEXT", nullable = false)
  private String message;

  @Column(nullable = false)
  @Builder.Default
  private Boolean isRead = false;

  @Column(length = 500)
  private String actionUrl;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "read_at")
  private LocalDateTime readAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "issue_id", nullable = false)
  private Issue issue;

  @PrePersist
  protected void onCreate() {
    createdAt = LocalDateTime.now();
  }

  public enum NotificationType {
    ISSUE_CREATED,
    ISSUE_UPDATED,
    ISSUE_ASSIGNED,
    ISSUE_RESOLVED,
    ISSUE_REGRESSED,
    COMMENT_ADDED,
    TEAM_INVITATION,
    PROJECT_ALERT
  }
}
