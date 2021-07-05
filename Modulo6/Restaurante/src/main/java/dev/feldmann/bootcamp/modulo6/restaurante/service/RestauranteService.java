package dev.feldmann.bootcamp.modulo6.restaurante.service;

import dev.feldmann.bootcamp.modulo6.restaurante.dto.MesaDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Mesa;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Pedido;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.PedidoStatus;
import dev.feldmann.bootcamp.modulo6.restaurante.entity.Prato;
import dev.feldmann.bootcamp.modulo6.restaurante.forms.PedidoForm;
import dev.feldmann.bootcamp.modulo6.restaurante.repository.MesaRepository;
import dev.feldmann.bootcamp.modulo6.restaurante.repository.PedidoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class RestauranteService {

    private MesaRepository mesaRepository;
    private PedidoRepository pedidoRepository;


    @SneakyThrows
    @Autowired
    public RestauranteService(MesaRepository mesaRepository, PedidoRepository pedidoRepository) {
        this.mesaRepository = mesaRepository;
        this.pedidoRepository = pedidoRepository;

        if (mesaRepository.all().isEmpty()) {
            createMesas(20);
        }
    }

    @SneakyThrows
    public void createMesas(int amount) {
        for (long i = 1; i <= amount; i++) {
            mesaRepository.save(new Mesa());
        }
    }

    @SneakyThrows
    public boolean fecharPedido(Long mesaId) {

        var mesa = mesaRepository.findById(mesaId);
        if (mesa.isEmpty()) {
            return false;
        }

        getPedidos(mesa.get()).stream().filter(Pedido::isAberto).forEach(Pedido::fechar);
        return true;
    }

    @SneakyThrows
    public Optional<MesaDTO> getMesaDTO(Long mesaId) {
        var mesa = mesaRepository.findById(mesaId);
        if (mesa.isEmpty()) {
            return Optional.empty();
        }
        var pedidos = getPedidos(mesa.get());
        var pendingPaymentValue = getPendingPaymentValue(mesa.get());
        return Optional.of(new MesaDTO(pendingPaymentValue, pedidos));
    }

    public Double getPendingPaymentValue(Mesa mesa) {
        var pedidos = getPedidos(mesa);
        return pedidos.stream()
                .filter(Pedido::isAberto)
                .mapToDouble(Pedido::calculateTotalPrice).sum();

    }


    @SneakyThrows
    public List<Pedido> getPedidos(Mesa mesa) {
        return pedidoRepository.all().stream()
                .filter(pedido -> pedido.getMesaId().equals(mesa.getId()))
                .collect(Collectors.toList());
    }


    public boolean isMesaOcupada(Mesa mesa) {
        var pedidos = getPedidos(mesa);
        return pedidos.stream().anyMatch(Pedido::isAberto);
    }

    @SneakyThrows
    public Long getMesasOcupadas() {
        return mesaRepository.all().stream()
                .filter(this::isMesaOcupada)
                .count();
    }

    @SneakyThrows
    public Long getMesasLivres() {
        return mesaRepository.all().stream()
                .filter(Predicate.not(this::isMesaOcupada))
                .count();
    }


    @SneakyThrows
    public Double getCaixa() {
        return pedidoRepository.all().stream()
                .filter(Pedido::isFechado)
                .mapToDouble(Pedido::calculateTotalPrice).sum();
    }

    @SneakyThrows
    public Double getDinheiroAReceber() {
        return pedidoRepository.all().stream()
                .filter(Pedido::isAberto)
                .mapToDouble(Pedido::calculateTotalPrice).sum();
    }

    @SneakyThrows
    public boolean fazerPedido(Long mesaId, PedidoForm form) {

        Optional<Mesa> mesa = mesaRepository.findById(mesaId);
        if (mesa.isEmpty()) {
            return false;
        }
        Pedido p = new Pedido(mesa.get().getId(), PedidoStatus.ABERTO);

        form.getPratos().stream()
                .map(pratoForm -> new Prato(pratoForm.getNome(), pratoForm.getPreco().doubleValue(), pratoForm.getDescription(), pratoForm.getQuantidade()))
                .forEach(p::addPrato);

        pedidoRepository.save(p);
        return true;
    }

}
