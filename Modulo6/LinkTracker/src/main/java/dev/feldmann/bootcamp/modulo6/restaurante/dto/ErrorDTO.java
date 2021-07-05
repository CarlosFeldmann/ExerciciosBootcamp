package dev.feldmann.bootcamp.modulo6.restaurante.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDTO {
    private String code;
    private String description;

}
