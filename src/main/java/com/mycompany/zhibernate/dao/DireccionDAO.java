package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Direccion;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface DireccionDAO {
    
    // Crear una nueva direccion
    void crear(Direccion direccion);

    // Leer una direccion por ID
    Direccion obtenerPorId(Long id);

    // Leer todas las direcciones
    List<Direccion> obtenerTodos();

    // Actualizar una direccion
    void actualizar(Direccion direccion);

    // Eliminar una direccion
    void eliminar(Direccion direccion);

    
    // Buscar direcciones por ciudad (ignorando mayúsculas y minúsculas)
    List<Direccion> findByCiudadContainingIgnoreCase(String ciudad);

    // Buscar sucursales por ciudad y ordenar por codigo postal de forma descendente
    List<Direccion> findByCiudadOrderByCpDesc(String  ciudad);
}
