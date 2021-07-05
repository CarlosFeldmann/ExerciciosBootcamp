package dev.feldmann.bootcamp.modulo6.restaurante.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
public class LinkDTO {

    private Long id;
    private String url;
    private Long clicks;
    private boolean active;


    public static LinkDTO fromLink(Link link) {
        return new LinkDTO(link.getId(), link.getUrl(), link.getClicks(), link.isValid());
    }
}
