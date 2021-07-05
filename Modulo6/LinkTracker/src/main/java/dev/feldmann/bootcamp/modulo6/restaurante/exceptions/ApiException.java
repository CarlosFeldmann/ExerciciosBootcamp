package dev.feldmann.bootcamp.modulo6.restaurante.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class ApiException extends RuntimeException {

    private String code;
    private String description;
    private HttpStatus responseStatus;


    public ApiException(String code, String description, HttpStatus responseStatus) {
        super(code);
        this.code = code;
        this.description = description;
        this.responseStatus = responseStatus;
    }

    public ApiException(String code, String description, HttpStatus responseStatus, Throwable cause) {
        super(code, cause);
        this.code = code;
        this.description = description;
        this.responseStatus = responseStatus;
    }
}
