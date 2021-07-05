package dev.feldmann.bootcamp.modulo6.restaurante.config;


import dev.feldmann.bootcamp.modulo6.restaurante.dto.ErrorDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.exceptions.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorDTO> handle(ApiException exception) {
        var dto = new ErrorDTO(exception.getCode(), exception.getDescription());
        return ResponseEntity.status(exception.getResponseStatus()).body(dto);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorDTO> routeNotFound(NoHandlerFoundException exception) {
        var apiError = new ApiException("route_not_found", "Route not found in application", HttpStatus.NOT_FOUND, exception);
        return handle(apiError);
    }


}
