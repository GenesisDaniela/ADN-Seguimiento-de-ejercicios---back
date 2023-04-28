package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.cliente.puerto.RepositorioCliente;
import com.ceiba.factura.modelo.entidad.EstadoFactura;
import com.ceiba.factura.modelo.entidad.Factura;
import com.ceiba.factura.puerto.repositorio.RepositorioProductoFacturar;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoFactura implements RowMapper<Factura>, MapperResult {

    private final RepositorioCliente repositorioCliente;
    private final RepositorioProductoFacturar repositorioProductoFacturar;

    public MapeoFactura(RepositorioCliente repositorioCliente, RepositorioProductoFacturar repositorioProductoFacturar) {
        this.repositorioCliente = repositorioCliente;
        this.repositorioProductoFacturar = repositorioProductoFacturar;
    }

    @Override
    public Factura mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        var id = resultSet.getLong("id");
        var idCliente = resultSet.getLong("id_cliente");
        var valorTotal = resultSet.getBigDecimal("valor_total");
        var estado = EstadoFactura.valueOf(resultSet.getString("estado"));

        return Factura.reconstruir(id, repositorioCliente.obtener(idCliente),
                repositorioProductoFacturar.obtenerPorFactura(id), valorTotal, estado);
    }

}
