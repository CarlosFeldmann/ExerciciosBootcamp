package dev.feldmann.bootcamp.modulo6.restaurante.exceptions;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends ApiException {

    public ResourceNotFoundException(Long id) {
        super("resource_not_found", "Resource not found with the given id(" + id + ")", HttpStatus.NOT_FOUND);
    }
}
