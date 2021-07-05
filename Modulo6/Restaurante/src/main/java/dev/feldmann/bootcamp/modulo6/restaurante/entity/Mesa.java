package dev.feldmann.bootcamp.modulo6.restaurante.entity;

import dev.feldmann.bootcamp.common.jsonRepository.Identifiable;
import lombok.Getter;

import java.time.LocalDateTime;


public class Mesa implements Identifiable {

    private Long id;
    @Getter
    private LocalDateTime createdAt;

    public Mesa() {
        createdAt = LocalDateTime.now();
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
