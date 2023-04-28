package com.ceiba.cliente;

import com.ceiba.BasePrueba;
import com.ceiba.cliente.entidad.TipoCliente;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    void deberiaCrearClienteExitoso(){

        var cliente = new ClienteTestDataBuilder()
                .conTipoCliente(TipoCliente.COMUN)
                .conId(1l)
                .conNombre("Cliente 1").reconstruir();

        Assertions.assertEquals("Cliente 1", cliente.getNombre());
        Assertions.assertEquals(TipoCliente.COMUN, cliente.getTipoCliente());
        Assertions.assertEquals(1l, cliente.getId());
    }

    @Test
    void reconstruirClienteSinTipoDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new ClienteTestDataBuilder()
                .conNombre("Cliente 1").reconstruir(),
                ExcepcionValorObligatorio.class,
                "Tipo de cliente es requerido");
    }

    @Test
    void reconstruirClienteSinNombreDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new ClienteTestDataBuilder()
                        .conTipoCliente(TipoCliente.COMUN)
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Nombre del cliente es requerido");
    }

    @Test
    void reconstruirClienteSinIdDeberiaLanzarError(){
        BasePrueba.assertThrows(()->new ClienteTestDataBuilder()
                        .conTipoCliente(TipoCliente.COMUN)
                        .conNombre("Cliente 1")
                        .reconstruir(),
                ExcepcionValorObligatorio.class,
                "Id del cliente es requerido");
    }

    @Test
    void deberiaREsponderEsClientePreferenciaCorrectamente(){
        var cliente = new ClienteTestDataBuilder()
                .conTipoCliente(TipoCliente.PREFERENCIAL)
                .conNombre("Cliente 1")
                .conId(1l)
                .reconstruir();
        Assertions.assertTrue(cliente.esTipoPreferencial());
        Assertions.assertFalse(cliente.esTipoEspecial());
    }

    @Test
    void deberiaREsponderEsClienteEspecialCorrectamente(){
        var cliente = new ClienteTestDataBuilder()
                .conTipoCliente(TipoCliente.ESPECIAL)
                .conNombre("Cliente 1")
                .conId(1l)
                .reconstruir();
        Assertions.assertFalse(cliente.esTipoPreferencial());
        Assertions.assertTrue(cliente.esTipoEspecial());
    }
}
