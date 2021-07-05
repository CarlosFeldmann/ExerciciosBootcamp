package dev.feldmann.bootcamp.modulo6.restaurante.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.feldmann.bootcamp.common.jsonRepository.Identifiable;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Pedido implements Identifiable {


    private Long id;
    private PedidoStatus status;
    private LocalDateTime createdAt;
    private Long mesaId;

    private List<Prato> pratos;

    public Pedido(Long mesaId, PedidoStatus status) {
        this.mesaId = mesaId;
        this.status = status;
        this.createdAt = LocalDateTime.now();
        this.pratos = new ArrayList<>();
    }

    public Double calculateTotalPrice() {
        return pratos.stream().mapToDouble(Prato::calculateTotalPrice).sum();
    }

    public void addPrato(Prato prato) {
        this.pratos.add(prato);
    }


    @JsonIgnore
    public boolean isFechado() {
        return this.status == PedidoStatus.FECHADO;
    }

    @JsonIgnore
    public boolean isAberto() {
        return this.status == PedidoStatus.ABERTO;
    }

    public void fechar() {
        status = PedidoStatus.FECHADO;
    }

}
