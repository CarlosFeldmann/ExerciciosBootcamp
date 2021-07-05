package dev.feldmann.bootcamp.common.jsonRepository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class JsonStorage<T> {
    private Long lastId = 1L;
    private Map<Long, T> entities;


    public JsonStorage(Long lastId, Map<Long, T> entities) {
        this.lastId = lastId;
        this.entities = entities;
    }


    @JsonIgnore
    public Long getNewId() {
        return lastId++;
    }
}
