package com.ceiba.producto.puerto;

import com.ceiba.producto.entidad.Producto;

public interface RepositorioProducto {

    Producto obtener(Long id);
}
