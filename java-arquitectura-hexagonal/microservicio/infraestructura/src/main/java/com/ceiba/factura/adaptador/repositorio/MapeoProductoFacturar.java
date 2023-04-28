package com.ceiba.factura.adaptador.repositorio;

import com.ceiba.factura.modelo.entidad.ProductoFacturar;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.producto.puerto.RepositorioProducto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class MapeoProductoFacturar implements RowMapper<ProductoFacturar>, MapperResult {

    private final RepositorioProducto repositorioProducto;

    public MapeoProductoFacturar(RepositorioProducto repositorioProducto) {
        this.repositorioProducto = repositorioProducto;
    }

    @Override
    public ProductoFacturar mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        var id = resultSet.getLong("id");
        var idProducto = resultSet.getLong("id_producto");
        var cantidad = resultSet.getInt("cantidad");

        return ProductoFacturar.reconstruir(id, cantidad, repositorioProducto.obtener(idProducto));
    }

}
