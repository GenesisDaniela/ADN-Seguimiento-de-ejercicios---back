package com.ceiba.cliente.entidad;

import com.ceiba.dominio.ValidadorArgumento;

public class Cliente {

    private Long id;
    private final String nombre;
    private final TipoCliente tipoCliente;

    private Cliente(Long id, String nombre, TipoCliente tipoCliente) {
        this.id = id;
        this.nombre = nombre;
        this.tipoCliente = tipoCliente;
    }

    public static Cliente reconstruir(Long id, String nombre, TipoCliente tipoCliente) {
        ValidadorArgumento.validarObligatorio(tipoCliente, "Tipo de cliente es requerido");
        ValidadorArgumento.validarObligatorio(nombre, "Nombre del cliente es requerido");
        ValidadorArgumento.validarObligatorio(id, "Id del cliente es requerido");
        return new Cliente(id, nombre, tipoCliente);
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }


    public boolean esTipoPreferencial() {
        return this.tipoCliente.equals(TipoCliente.PREFERENCIAL);
    }

    public boolean esTipoEspecial() {
        return this.tipoCliente.equals(TipoCliente.ESPECIAL);
    }

    public boolean esTipoComun() {
        return this.tipoCliente.equals(TipoCliente.COMUN);
    }
}
