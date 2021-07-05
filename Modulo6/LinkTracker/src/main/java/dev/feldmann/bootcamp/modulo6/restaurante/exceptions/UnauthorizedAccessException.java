package dev.feldmann.bootcamp.modulo6.restaurante.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedAccessException extends ApiException {

    public UnauthorizedAccessException(Long id) {
        super("unauthorized_access", "Unauthorized access for the given resource (" + id + ")", HttpStatus.UNAUTHORIZED);
    }
}
