package com.ceiba.producto.entidad;

import com.ceiba.dominio.ValidadorArgumento;

import java.math.BigDecimal;

public class Producto {
    private final Long id;
    private final String nombre;
    private final Boolean aplicaIva;
    private final BigDecimal valor;

    private Producto(Long id, String nombre, boolean aplicaIva, BigDecimal valor) {
        this.id = id;
        this.nombre = nombre;
        this.aplicaIva = aplicaIva;
        this.valor = valor;
    }

    public static Producto reconstruir(Long id, String nombre, Boolean aplicaIva, BigDecimal valor) {
        ValidadorArgumento.validarObligatorio(id, "El id del producto es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "El nombre del producto es requerido");
        ValidadorArgumento.validarObligatorio(aplicaIva, "Aplica Iva es requerido");
        ValidadorArgumento.validarObligatorio(valor, "Valor es requerido para el producto");
        return new Producto(id, nombre, aplicaIva, valor);

    }

    public String getNombre() {
        return nombre;
    }

    public Long getId() {
        return id;
    }

    public boolean aplicaIva() {
        return aplicaIva;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
