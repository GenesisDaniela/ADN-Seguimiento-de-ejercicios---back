package com.ceiba.producto;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class ProductoTest {

    @Test
    void deberiaReconstruirProductoExitosamente() {

        var producto = new ProductoTestDataBuilder().conId(1l)
                .conNombre("Arroz")
                .conAplicaIva(true)
                .conValor(BigDecimal.valueOf(2500)).reconstruir();

        Assertions.assertEquals(1l, producto.getId());
        Assertions.assertEquals("Arroz", producto.getNombre());
        Assertions.assertEquals(true, producto.aplicaIva());
        Assertions.assertEquals(BigDecimal.valueOf(2500), producto.getValor());
    }

    @Test
    void reconstruirSinIddeberiaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conNombre("Arroz")
                        .conAplicaIva(true)
                        .conValor(BigDecimal.valueOf(2500)).reconstruir(), ExcepcionValorObligatorio.class,
                "El id del producto es requerido");
    }

    @Test
    void reconstruirSinNombredeberiaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(1l)
                        .conAplicaIva(true)
                        .conValor(BigDecimal.valueOf(2500)).reconstruir(), ExcepcionValorObligatorio.class,
                "El nombre del producto es requerido");
    }

    @Test
    void reconstruirSinAplicaIvadeberiaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(1l)
                        .conNombre("Arroz")
                        .conValor(BigDecimal.valueOf(2500)).reconstruir(), ExcepcionValorObligatorio.class,
                "Aplica Iva es requerido");
    }

    @Test
    void reconstruirSinValorIvadeberiaDeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new ProductoTestDataBuilder()
                        .conId(1l)
                        .conNombre("Arroz")
                        .conAplicaIva(true)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "Valor es requerido para el producto");
    }
}
