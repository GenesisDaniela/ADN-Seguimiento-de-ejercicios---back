package com.ceiba.factura;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.ClienteTestDataBuilder;
import com.ceiba.cliente.entidad.Cliente;
import com.ceiba.cliente.entidad.TipoCliente;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.producto.ProductoTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;

class FacturaTest {


    @Test
    void deberiaCrearLaFacturaCorrectamenteClientePreferenccial() {
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .conTipoCliente(TipoCliente.PREFERENCIAL)
                .reconstruir();
        ProductoFacturar productoFacturarArroz = new ProductoFacturarTestDataBuilder()
                .conCantidad(5)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(2000))
                        .conAplicaIva(true).conNombre("Arroz")
                        .reconstruir())
                .build();
        ProductoFacturar productoFacturarCarne = new ProductoFacturarTestDataBuilder()
                .conCantidad(6)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conAplicaIva(false)
                        .conValor(BigDecimal.valueOf(10000)).conNombre("carne")
                        .reconstruir())
                .build();

        var factura = new FacturaTestDataBuilder()
                .conProducto(productoFacturarCarne)
                .conProducto(productoFacturarArroz)
                .conCliente(cliente).crear();

        Assertions.assertEquals(cliente, factura.getCliente());
        Assertions.assertEquals(productoFacturarArroz, factura.getProductos().get(1));
        Assertions.assertEquals(productoFacturarCarne, factura.getProductos().get(0));
        Assertions.assertEquals(57520l, factura.getValorTotal().longValue());
        Assertions.assertTrue(factura.esActiva());

    }

    @Test
    void deberiaReconstruirLaFacturaCorrectamente() {
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .reconstruir();
        ProductoFacturar productoFacturarArroz = new ProductoFacturarTestDataBuilder()
                .conCantidad(5)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto().reconstruir())
                .build();

        var factura = new FacturaTestDataBuilder().conId(1l)
                .conCliente(cliente)
                .conProducto(productoFacturarArroz)
                .conEstado(EstadoFactura.ANULADA)
                .conValorTotal(BigDecimal.valueOf(25000)).reconstruir();


        Assertions.assertEquals(cliente, factura.getCliente());
        Assertions.assertEquals(productoFacturarArroz, factura.getProductos().get(0));
        Assertions.assertEquals(25000l, factura.getValorTotal().longValue());
        Assertions.assertEquals(1l, factura.getId());
        Assertions.assertTrue(factura.esAnulada());
        Assertions.assertFalse(factura.esActiva());
    }

    @Test
    void deberiaCrearLaFacturaCorrectamenteClienteEspecial() {
        Cliente cliente = new ClienteTestDataBuilder()
                .conClientePorDefecto()
                .conTipoCliente(TipoCliente.ESPECIAL)
                .reconstruir();
        ProductoFacturar productoFacturarArroz = new ProductoFacturarTestDataBuilder()
                .conCantidad(5)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conValor(BigDecimal.valueOf(2000))
                        .conAplicaIva(true).conNombre("Arroz")
                        .reconstruir())
                .build();
        ProductoFacturar productoFacturarCarne = new ProductoFacturarTestDataBuilder()
                .conCantidad(6)
                .conProducto(new ProductoTestDataBuilder()
                        .conProductoPorDefecto()
                        .conAplicaIva(false)
                        .conValor(BigDecimal.valueOf(10000)).conNombre("carne")
                        .reconstruir())
                .build();

        var factura = new FacturaTestDataBuilder().conProducto(productoFacturarArroz)
                .conProducto(productoFacturarCarne)
                .conCliente(cliente).crear();

        Assertions.assertEquals(cliente, factura.getCliente());
        Assertions.assertEquals(productoFacturarArroz, factura.getProductos().get(0));
        Assertions.assertEquals(productoFacturarCarne, factura.getProductos().get(1));
        Assertions.assertEquals(64710l, factura.getValorTotal().longValue());

    }

    @Test
    void facturaSinClientedeberiaLanzarError() {


        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder().conFacturaPorDefecto()
                        .conCliente(null)
                        .crear(), ExcepcionValorObligatorio.class,
                "El cliente es requerido para facturar");
    }

    @Test
    void facturaSinProductosdeberiaLanzarError() {
        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder().conFacturaPorDefecto()
                        .conProductos(Arrays.asList()).crear(), ExcepcionValorObligatorio.class,
                "No se puede crear una factura sin productos");
    }


    @Test
    void reconstruirFacturaSinClientedeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder().
                        conFacturaPorDefecto()
                        .conCliente(null).reconstruir(), ExcepcionValorObligatorio.class,
                "El cliente es requerido para facturar");
    }

    @Test
    void reconstruirSinProductosdeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conFacturaPorDefecto()
                        .conProductos(Arrays.asList()).reconstruir(), ExcepcionValorObligatorio.class,
                "No se puede crear una factura sin productos");
    }

    @Test
    void reconstruirValorTotalMenorACeroLanzarError() {

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conFacturaPorDefecto()
                        .conValorTotal(BigDecimal.valueOf(-1))
                        .reconstruir(), ExcepcionValorInvalido.class,
                "El total no puede ser menor a cero");
    }

    @Test
    void reconstruirSinIdDeberiaLanzarError() {

        BasePrueba.assertThrows(() -> new FacturaTestDataBuilder()
                        .conFacturaPorDefecto()
                        .conId(null)
                        .reconstruir(), ExcepcionValorObligatorio.class,
                "El id es requerido");
    }

    @Test
    void anularFacturaDeberiaQuedarEnEstadoAnulada() {
        var factura = new FacturaTestDataBuilder().conFacturaPorDefecto()
                .conCliente(new ClienteTestDataBuilder()
                        .conClientePorDefecto()
                        .conTipoCliente(TipoCliente.PREFERENCIAL)
                        .reconstruir())
                .crear();

        factura.anular();

        Assertions.assertTrue(factura.esAnulada());
    }

    @Test
    void anularFacturaDeClienteComunDeberiaLanzarError() {
        var factura = new FacturaTestDataBuilder()
                .conFacturaPorDefecto()
                .conCliente(new ClienteTestDataBuilder().conClientePorDefecto()
                        .conTipoCliente(TipoCliente.COMUN).reconstruir())
                .crear();

        BasePrueba.assertThrows(() -> factura.anular(), ExcepcionValorInvalido.class, "No se puede anular la factura de un cliente comun");

    }


}
