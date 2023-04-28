package com.ceiba.factura.controlador;

import com.ceiba.factura.comando.ComandoProductoFacturar;
import com.ceiba.factura.comando.ComandoSolicitudFacturar;

import java.util.ArrayList;
import java.util.List;


public class ComandoFacturarTestDataBuilder {

    private Long idCliente;
    private List<ComandoProductoFacturar> comandoProductosFacturar;

    public ComandoFacturarTestDataBuilder() {
        this.comandoProductosFacturar = new ArrayList<>();
    }

    public ComandoFacturarTestDataBuilder crearPorDefecto() {
        this.idCliente = 1l;
        this.comandoProductosFacturar.add(new ComandoProductoFacturar(1l, 5));
        this.comandoProductosFacturar.add(new ComandoProductoFacturar(2l, 7));
        return this;

    }

    public ComandoSolicitudFacturar build() {
        return new ComandoSolicitudFacturar(this.idCliente, comandoProductosFacturar);
    }
}
