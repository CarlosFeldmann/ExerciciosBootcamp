package dev.feldmann.bootcamp.modulo6.restaurante.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
public class Prato {


    private String nome;
    private Double price;
    private String description;
    private Integer amount;


    public Prato(String nome, Double price, String description, Integer amount) {
        this.nome = nome;
        this.price = price;
        this.description = description;
        this.amount = amount;
    }

    public Double calculateTotalPrice() {
        return price * amount;
    }
}

