package dev.feldmann.bootcamp.modulo6.restaurante.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.feldmann.bootcamp.common.jsonRepository.JsonRepository;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Mesa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MesaRepository extends JsonRepository<Mesa> {
    @Autowired
    public MesaRepository(ObjectMapper mapper) {
        super(mapper, "./storage/mesaStorage.json");
    }
}
