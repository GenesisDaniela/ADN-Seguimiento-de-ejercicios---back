package com.ceiba.producto.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.producto.entidad.Producto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoProducto implements RowMapper<Producto>, MapperResult {

    @Override
    public Producto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var id = resultSet.getLong("id");
        var nombre = resultSet.getString("nombre");
        var aplicaIva = resultSet.getBoolean("aplica_iva");
        var valor = resultSet.getBigDecimal("valor");

        return Producto.reconstruir(id, nombre, aplicaIva, valor);
    }

}
