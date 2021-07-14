package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses;

import java.util.List;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ValidationError extends ApiError {

  private List<FieldValidationError> violations;

  public ValidationError(HttpStatus statusCode, List<FieldValidationError> fieldErrors) {
    super(
        "field_constraint_violation", "One or more fields validation failed!", statusCode.value());
    this.violations = fieldErrors;
  }
}
