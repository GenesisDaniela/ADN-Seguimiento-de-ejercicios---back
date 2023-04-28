package com.ceiba.infraestructura.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;

public class EjecucionBaseDeDatos {

    public static <T> T obtenerUnObjetoONull(EjecutarBD<T> ejecutarBd) {
        try {
            return ejecutarBd.ejecutar();
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
