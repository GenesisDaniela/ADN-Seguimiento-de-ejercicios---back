package com.ceiba.factura;

import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.producto.ProductoTestDataBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FacturaTestDataBuilder {
    private Long id;
    private Cliente cliente;
    private List<ProductoFacturar> productosFacturar;
    private BigDecimal valorTotal;
    private EstadoFactura estadoFactura;

    public FacturaTestDataBuilder() {
        productosFacturar = new ArrayList<>();
    }

    public FacturaTestDataBuilder conFacturaPorDefecto() {
        this.id = 5l;
        this.cliente = new ClienteTestDataBuilder().conClientePorDefecto().reconstruir();
        var producto = new ProductoTestDataBuilder().conProductoPorDefecto().reconstruir();
        this.conProducto(new ProductoFacturarTestDataBuilder().conCantidad(5).conProducto(producto).conId(2l).reconstruir());
        this.valorTotal = BigDecimal.valueOf(36000);
        this.estadoFactura = EstadoFactura.ACTIVA;
        return this;
    }

    public FacturaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public FacturaTestDataBuilder conCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public FacturaTestDataBuilder conProducto(ProductoFacturar productoFacturar) {
        this.productosFacturar.add(productoFacturar);
        return this;
    }

    public FacturaTestDataBuilder conProductos(List<ProductoFacturar> productosFacturar) {
        this.productosFacturar = productosFacturar;
        return this;
    }

    public FacturaTestDataBuilder conValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }


    public FacturaTestDataBuilder conEstado(EstadoFactura estadoFactura) {
        this.estadoFactura = estadoFactura;
        return this;
    }

    public Factura crear() {
        return Factura.crear(new SolicitudFacturarTestDataBuilder()
                .conProductosFacturar(productosFacturar)
                .conCliente(cliente)
                .build());
    }

    public Factura reconstruir() {
        return Factura.reconstruir(id, cliente, productosFacturar, valorTotal, estadoFactura);
    }

}
