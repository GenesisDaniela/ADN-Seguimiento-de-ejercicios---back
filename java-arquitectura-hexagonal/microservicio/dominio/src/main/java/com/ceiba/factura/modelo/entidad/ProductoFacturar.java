package com.ceiba.factura.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.producto.entidad.Producto;

import java.math.BigDecimal;

public class ProductoFacturar {
    public static final Double VALOR_IVA = 0.19;

    private Long id;
    private final Integer cantidad;
    private final Producto producto;

    private ProductoFacturar(Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
    }

    private ProductoFacturar(Long id, Integer cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        this.id = id;
    }

    public static ProductoFacturar crear(Integer cantidad, Producto producto) {
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        return new ProductoFacturar(cantidad, producto);
    }

    public static ProductoFacturar reconstruir(Long id, Integer cantidad, Producto producto) {
        ValidadorArgumento.validarObligatorio(cantidad, "Cantidad es requerida");
        ValidadorArgumento.validarObligatorio(producto, "Producto es requerido");
        return new ProductoFacturar(id, cantidad, producto);
    }

    public BigDecimal calcularIva() {
        if (producto.aplicaIva()) {
            return calcularSubTotal().multiply(BigDecimal.valueOf(VALOR_IVA));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal calcularTotalConIva() {
        return calcularSubTotal()
                .add(calcularIva());
    }

    private BigDecimal calcularSubTotal() {
        return producto.getValor()
                .multiply(BigDecimal.valueOf(cantidad));
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public Long getId() {
        return id;
    }
}
