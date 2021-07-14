package dev.feldmann.bootcamp.wave2.diplomas.config.exceptions;

import dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses.ApiError;
import dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses.FieldValidationError;
import dev.feldmann.bootcamp.wave2.diplomas.config.exceptions.responses.ValidationError;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {


  /**
   * This exception is thrown when a body validation fails
   *
   * @param exception - Exception to be handled
   * @return Human friendly response
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ValidationError> handleInvalidInput(
      MethodArgumentNotValidException exception) {
    var fieldErrors =
        exception.getFieldErrors().stream()
                 .map(fieldError -> new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

    var response = new ValidationError(HttpStatus.BAD_REQUEST, fieldErrors);

    return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  /**
   * This exception is thrown when a request param validation fails
   *
   * @param exception - Exception to be handled
   * @return Human friendly response
   */

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ValidationError> handleInvalidInput(
      ConstraintViolationException exception) {

    var fieldErrors =
        exception.getConstraintViolations().stream()
            .map(
                violation ->
                    new FieldValidationError(
                        violation.getPropertyPath().toString(),
                        violation.getMessage(),
                        violation.getInvalidValue()))
            .collect(Collectors.toList());

    var dto = new ValidationError(HttpStatus.BAD_REQUEST, fieldErrors);

    return ResponseEntity.status(dto.getStatusCode()).body(dto);
  }

  /**
   * This exception is thrown when the server can't parse the user input
   *
   * @param exception - Exception to be handled
   * @return Human friendly response
   */
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> handleInvalidContent(HttpMessageNotReadableException exception) {
    var response =
        new ApiError(
            "bad_request", "Unable to parse request body!", HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(response.getStatusCode()).body(response);
  }

  /**
   * This exception is thrown when we don't have the route mapping that the user is looking for
   *
   * @param exception - Exception to be handled
   * @return Human friendly response
   */
  @ExceptionHandler(NoHandlerFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<ApiError> noRouteFound(
      HttpServletRequest req, NoHandlerFoundException exception) {
    ApiError apiError =
        new ApiError(
            "route_not_found",
            String.format("Route %s not found", req.getRequestURI()),
            HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(apiError.getStatusCode()).body(apiError);
  }
}
