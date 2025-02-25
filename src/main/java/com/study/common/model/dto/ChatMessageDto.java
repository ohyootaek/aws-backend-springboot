package com.study.common.model.dto;

import java.time.LocalDateTime;

public class ChatMessageDto {

  private String sender;
  private String message;
  private LocalDateTime timestamp;

  public ChatMessageDto() {}

  public ChatMessageDto(String sender, String message) {
    this.sender = sender;
    this.message = message;
    this.timestamp = LocalDateTime.now();
  }

  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }
}
