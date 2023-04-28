package com.ceiba.cliente.puerto;

import com.ceiba.cliente.entidad.Cliente;

public interface RepositorioCliente {

    Cliente obtener(Long id);
}
