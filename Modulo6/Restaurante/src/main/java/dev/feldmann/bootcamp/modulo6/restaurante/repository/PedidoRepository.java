package dev.feldmann.bootcamp.modulo6.restaurante.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.feldmann.bootcamp.common.jsonRepository.JsonRepository;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PedidoRepository extends JsonRepository<Pedido> {
    @Autowired
    public PedidoRepository(ObjectMapper mapper) {
        super(mapper, "./storage/pedidoStorage.json");
    }
}
