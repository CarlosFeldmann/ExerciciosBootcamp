package dev.feldmann.bootcamp.modulo6.restaurante.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.feldmann.bootcamp.common.jsonRepository.JsonRepository;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Link;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LinkRepository extends JsonRepository<Link> {
    @Autowired
    public LinkRepository(ObjectMapper mapper) {
        super(mapper, "storage/links.json");
    }
}
