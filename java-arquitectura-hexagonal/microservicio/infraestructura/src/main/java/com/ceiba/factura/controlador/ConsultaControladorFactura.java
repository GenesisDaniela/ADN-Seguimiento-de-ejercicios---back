package com.ceiba.factura.controlador;

import com.ceiba.factura.consulta.ManejadorConsultarFacturasAnuladas;
import com.ceiba.factura.modelo.dto.ResumenFacturaDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/factura")
@Tag(name = "Controlador consulta factura")
public class ConsultaControladorFactura {

    private final ManejadorConsultarFacturasAnuladas manejadorConsultarFacturasAnuladas;

    public ConsultaControladorFactura(ManejadorConsultarFacturasAnuladas manejadorConsultarFacturasAnuladas) {
        this.manejadorConsultarFacturasAnuladas = manejadorConsultarFacturasAnuladas;
    }

    @GetMapping("anulada")
    @Operation(summary = "Anular", description = "Metodo utilizado para consultar las facturas anuladas")
    public List<ResumenFacturaDTO> obtenerAnuladas() {
        return manejadorConsultarFacturasAnuladas.ejecutar();
    }
}
