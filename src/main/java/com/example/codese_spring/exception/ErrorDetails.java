package com.example.codese_spring.exception;

import java.sql.Timestamp;

public class ErrorDetails {

  private Timestamp timestamp;
  private String details;
  private String message;

  public ErrorDetails(Timestamp timestamp, String details, String message) {
    this.timestamp = timestamp;
    this.details = details;
    this.message = message;
  }

  public Timestamp getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Timestamp timestamp) {
    this.timestamp = timestamp;
  }

  public String getDetails() {
    return details;
  }

  public void setDetails(String details) {
    this.details = details;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
