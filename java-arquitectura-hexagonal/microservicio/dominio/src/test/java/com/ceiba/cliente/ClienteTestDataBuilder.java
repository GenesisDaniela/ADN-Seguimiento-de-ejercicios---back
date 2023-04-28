package com.ceiba.cliente;

import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.cliente.entidad.TipoCliente;

public class ClienteTestDataBuilder {
    private String nombre;
    private TipoCliente tipoCliente;
    private Long id;

    public ClienteTestDataBuilder conClientePorDefecto(){
        this.nombre = "Cliente 1";
        this.tipoCliente = TipoCliente.COMUN;
        this.id = 1l;
        return this;
    }

    public ClienteTestDataBuilder conNombre(String nombre){
        this.nombre = nombre;
        return this;
    }

    public ClienteTestDataBuilder conTipoCliente(TipoCliente tipoCliente){
        this.tipoCliente = tipoCliente;
        return this;
    }

    public ClienteTestDataBuilder conId(Long id){
        this.id = id;
        return this;
    }

    public Cliente reconstruir() {
        return Cliente.reconstruir(id, nombre, tipoCliente);
    }
}
