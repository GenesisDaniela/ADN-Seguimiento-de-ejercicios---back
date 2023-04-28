package com.ceiba.factura.modelo.entidad;

import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Factura {

    public static final double DESCUENTO_CLIENTE_PREFERENCIAL = 0.2;
    public static final double DESCUENTO_CLIENTE_ESPECIAL = 0.1;

    private Long id;
    private Cliente cliente;
    private List<ProductoFacturar> productosFacturar;
    private BigDecimal valorTotal;
    private EstadoFactura estado;

    private Factura(Cliente cliente, List<ProductoFacturar> productosFacturar) {
        this.cliente = cliente;
        this.productosFacturar = new ArrayList<>(productosFacturar);
        this.valorTotal = calcularvalorTotal(productosFacturar);
        aplicarDescuento();
        this.estado = EstadoFactura.ACTIVA;
    }

    private Factura(Long id, Cliente cliente, List<ProductoFacturar> productosFacturar, BigDecimal valorTotal, EstadoFactura estadoFactura) {
        this.cliente = cliente;
        this.productosFacturar = new ArrayList<>(productosFacturar);
        this.id = id;
        this.valorTotal = valorTotal;
        this.estado = estadoFactura;
    }

    private BigDecimal calcularvalorTotal(List<ProductoFacturar> productosFacturar) {
        return productosFacturar.stream()
                .map(ProductoFacturar::calcularTotalConIva)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void aplicarDescuento() {
        if (this.cliente.esTipoPreferencial()) {
            this.valorTotal = valorTotal.subtract(valorTotal.multiply(BigDecimal.valueOf(DESCUENTO_CLIENTE_PREFERENCIAL)));
        } else if (this.cliente.esTipoEspecial()) {
            this.valorTotal = valorTotal.subtract(valorTotal.multiply(BigDecimal.valueOf(DESCUENTO_CLIENTE_ESPECIAL)));
        }
    }

    public void anular() {
        if(this.cliente.esTipoComun()){
            throw new ExcepcionValorInvalido("No se puede anular la factura de un cliente comun");
        }
        this.estado = EstadoFactura.ANULADA;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public List<ProductoFacturar> getProductos() {
        return Collections.unmodifiableList(productosFacturar);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Long getId() {
        return id;
    }

    public static Factura crear(SolicitudFacturar solicitudFacturar) {
        ValidadorArgumento.validarObligatorio(solicitudFacturar.getCliente(), "El cliente es requerido para facturar");
        ValidadorArgumento.validarNoVacio(solicitudFacturar.getProductosFacturar(), "No se puede crear una factura sin productos");
        return new Factura(solicitudFacturar.getCliente(), solicitudFacturar.getProductosFacturar());
    }

    public static Factura reconstruir(Long id, Cliente cliente, List<ProductoFacturar> productosFacturar, BigDecimal valorTotal, EstadoFactura estadoFactura) {
        ValidadorArgumento.validarObligatorio(cliente, "El cliente es requerido para facturar");
        ValidadorArgumento.validarNoVacio(productosFacturar, "No se puede crear una factura sin productos");
        ValidadorArgumento.validarObligatorio(id, "El id es requerido");
        if (valorTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ExcepcionValorInvalido("El total no puede ser menor a cero");
        }
        return new Factura(id, cliente, productosFacturar, valorTotal, estadoFactura);
    }


    public Boolean esAnulada() {
        return this.estado.equals(EstadoFactura.ANULADA);
    }

    public boolean esActiva() {
        return this.estado.equals(EstadoFactura.ACTIVA);
    }

    public EstadoFactura getEstado() {
        return estado;
    }
}
