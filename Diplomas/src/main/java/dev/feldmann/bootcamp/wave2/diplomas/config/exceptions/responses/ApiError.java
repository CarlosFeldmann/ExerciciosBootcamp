package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses;

import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class ApiError {
  private String code;

  private String description;

  private Integer statusCode;

  private LocalDateTime timestamp;


  public ApiError(String code, String description, Integer statusCode) {
    this.code = code;
    this.description = description;
    this.statusCode = statusCode;
    this.timestamp = LocalDateTime.now();
  }
}
