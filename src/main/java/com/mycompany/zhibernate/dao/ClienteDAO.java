package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Cliente;
import com.mycompany.zhibernate.modelo.Sucursal;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface ClienteDAO {
    
    // Crear un nuevo cliente
    void crear(Cliente cliente);

    // Leer un cliente por ID
    Cliente obtenerPorId(Long id);

    // Leer todos los clientes
    List<Cliente> obtenerTodos();

    // Actualizar un cliente
    void actualizar(Cliente cliente);

    // Eliminar un cliente
    void eliminar(Cliente cliente);

    
    // Buscar clientes por nombre (ignorando mayúsculas y minúsculas)
    List<Cliente> findByNombreContainingIgnoreCase(String nombre);

    // Buscar sucursales por nombre y ordenar por numero de sucursal de forma descendente
    List<Cliente> findByNombreOrderByNombreDesc(String  nombre);
}
