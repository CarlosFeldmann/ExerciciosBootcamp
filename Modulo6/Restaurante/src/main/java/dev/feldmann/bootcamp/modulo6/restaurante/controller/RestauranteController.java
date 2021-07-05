package dev.feldmann.bootcamp.modulo6.restaurante.controller;

import dev.feldmann.bootcamp.modulo6.restaurante.service.RestauranteService;
import dev.feldmann.bootcamp.modulo6.restaurante.dto.MesaDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.dto.RestauranteInfoDTO;
import dev.feldmann.bootcamp.modulo6.restaurante.forms.PedidoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/restaurante")
public class RestauranteController {

    @Autowired
    private RestauranteService service;

    @PostMapping("/mesa/{id}/pedido")
    public ResponseEntity<?> fazerPedido(
            @NotNull @Min(0) @PathVariable Long id,
            @Valid @RequestBody PedidoForm form) {

        boolean success = service.fazerPedido(id, form);
        if (!success) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mesa/{id}/pedirConta")
    public ResponseEntity<MesaDTO> fecharPedidos(@NotNull @Min(0) @PathVariable Long id) {
        if (service.fecharPedido(id)) {
            var dto = service.getMesaDTO(id);
            return ResponseEntity.ok(dto.get());
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/mesa/{id}")
    public ResponseEntity<MesaDTO> verPedidos(@NotNull @Min(0) @PathVariable Long id) {
        var dto = service.getMesaDTO(id);

        if (dto.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto.get());
    }


    @GetMapping
    public RestauranteInfoDTO restauranteInfo() {
        return new RestauranteInfoDTO(
                service.getCaixa(),
                service.getDinheiroAReceber(),
                service.getMesasOcupadas(),
                service.getMesasLivres()
        );

    }

}
