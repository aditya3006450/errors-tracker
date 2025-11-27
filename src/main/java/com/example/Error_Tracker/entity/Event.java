package com.example.Error_Tracker.entity;

import java.time.LocalDateTime;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "events")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
  @Id
  private ObjectId id;

  @Field("project_id")
  private String projectId;

  @Field("issue_id")
  private String issueId;

  @Field("event_id")
  private String eventId;

  @Field("level")
  private String level;

  @Field("logger")
  private String logger;

  @Field("title")
  private String title;

  @Field("message")
  private String message;

  @Field("exception")
  private ExceptionData exception;

  @Field("stacktrace")
  private StacktraceData stacktrace;

  @Field("breadcrumbs")
  private java.util.List<Breadcrumb> breadcrumbs;

  @Field("context")
  private Map<String, Object> context;

  @Field("tags")
  private Map<String, String> tags;

  @Field("environment")
  private String environment;

  @Field("release")
  private String release;

  @Field("platform")
  private String platform;

  @Field("sdk")
  private SdkInfo sdk;

  @Field("request")
  private RequestData request;

  @Field("user")
  private UserData user;

  @Field("fingerprint")
  private java.util.List<String> fingerprint;

  @Field("timestamp")
  private LocalDateTime timestamp;

  @Field("received")
  private LocalDateTime received;

  @Field("created_at")
  private LocalDateTime createdAt;

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class ExceptionData {
    @Field("type")
    private String type;

    @Field("value")
    private String value;

    @Field("module")
    private String module;

    @Field("stacktrace")
    private StacktraceData stacktrace;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class StacktraceData {
    @Field("frames")
    private java.util.List<Frame> frames;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Frame {
    @Field("filename")
    private String filename;

    @Field("function")
    private String function;

    @Field("module")
    private String module;

    @Field("lineno")
    private Integer lineno;

    @Field("colno")
    private Integer colno;

    @Field("abs_path")
    private String absPath;

    @Field("context_line")
    private String contextLine;

    @Field("pre_context")
    private java.util.List<String> preContext;

    @Field("post_context")
    private java.util.List<String> postContext;

    @Field("vars")
    private Map<String, Object> vars;

    @Field("in_app")
    private Boolean inApp;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Breadcrumb {
    @Field("timestamp")
    private LocalDateTime timestamp;

    @Field("level")
    private String level;

    @Field("category")
    private String category;

    @Field("message")
    private String message;

    @Field("data")
    private Map<String, Object> data;

    @Field("type")
    private String type;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class SdkInfo {
    @Field("name")
    private String name;

    @Field("version")
    private String version;

    @Field("integrations")
    private java.util.List<String> integrations;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class RequestData {
    @Field("url")
    private String url;

    @Field("method")
    private String method;

    @Field("query_string")
    private String queryString;

    @Field("headers")
    private Map<String, String> headers;

    @Field("cookies")
    private Map<String, String> cookies;

    @Field("body_size")
    private Long bodySize;

    @Field("env")
    private Map<String, String> env;
  }

  @Getter
  @Setter
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public static class UserData {
    @Field("id")
    private String id;

    @Field("username")
    private String username;

    @Field("email")
    private String email;

    @Field("ip_address")
    private String ipAddress;

    @Field("geo")
    private Map<String, String> geo;

    @Field("other")
    private Map<String, Object> other;
  }
}
