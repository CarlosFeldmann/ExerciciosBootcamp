package dev.feldmann.bootcamp.modulo6.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.feldmann.bootcamp.common.jsonRepository.Identifiable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
public class Link implements Identifiable {

    private Long id;
    private String url;
    // password hash using bcrypt
    private String password;
    private long clicks;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

    private Link() {
    }

    public Link(String url) {
        this(url, null);
    }

    public Link(String url, String password) {
        this.url = url;
        this.clicks = 0;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }


    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    @JsonIgnore
    public boolean isValid() {
        return deletedAt == null;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void click() {
        this.clicks++;
    }
}
