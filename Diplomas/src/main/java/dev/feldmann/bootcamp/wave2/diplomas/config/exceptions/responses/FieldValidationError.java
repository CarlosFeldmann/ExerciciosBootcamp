package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldValidationError {

  private String field;

  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object invalidValue;

  public FieldValidationError(String field, String message) {
    this.field = field;
    this.message = message;
  }
}
