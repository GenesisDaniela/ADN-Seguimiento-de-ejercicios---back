package com.ceiba.factura.comando.fabrica;

import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.factura.comando.ComandoProductoFacturar;
import com.ceiba.factura.comando.ComandoSolicitudFacturar;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.factura.modelo.entidad.SolicitudFacturar;
import com.ceiba.producto.puerto.RepositorioProducto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FabricaSolicitudFacturar {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioProducto repositorioProducto;

    public FabricaSolicitudFacturar(RepositorioCliente repositorioCliente, RepositorioProducto repositorioProducto) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProducto = repositorioProducto;
    }

    public SolicitudFacturar crear(ComandoSolicitudFacturar comandoSolicitudFacturar) {
        return new SolicitudFacturar(repositorioCliente.obtener(comandoSolicitudFacturar.getIdCliente()),
                obtenerProductos(comandoSolicitudFacturar.getComandoProductosFacturar())
        );
    }

    private List<ProductoFacturar> obtenerProductos(List<ComandoProductoFacturar> comandoProductosFacturar) {
        return comandoProductosFacturar.stream().map(comandoProducto ->
                        ProductoFacturar.crear(
                                comandoProducto.getCantidad(),
                                repositorioProducto.obtener(comandoProducto.getIdProducto())))
                .toList();
    }
}
