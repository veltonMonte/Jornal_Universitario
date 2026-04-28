package com.jornal.domain.valueobjects;

public class CorHex {

    private final String valor;

    public CorHex(String valor) {
        if (valor == null || !valor.matches("^#([A-Fa-f0-9]{6})$")) {
            throw new IllegalArgumentException("Cor inválida. Use formato hex: #RRGGBB");
        }
        this.valor = valor.toUpperCase();
    }

    public String getValor() { return valor; }

    @Override
    public String toString() { return valor; }
}
