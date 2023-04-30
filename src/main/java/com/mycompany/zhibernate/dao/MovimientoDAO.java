package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Movimiento;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface MovimientoDAO {
    
// Crear un nuevo movimiento
    void crear(Movimiento movimiento);

    // Leer un movimiento por ID
    Movimiento obtenerPorId(Long id);

    // Leer todas los movimientos
    List<Movimiento> obtenerTodos();

    // Actualizar un movimiento
    void actualizar(Movimiento movimiento);

    // Eliminar un movimiento
    void eliminar(Movimiento movimiento);

    
    // Buscar movimientos por idMovimiento (ignorando mayúsculas y minúsculas)
    List<Movimiento> findByIdMovimientoContainingIgnoreCase(String idMovimiento);

    // Buscar movimientos por concepto y ordenar por cantidad de forma descendente
    List<Movimiento> findByConceptoOrderByCantidadDesc(String  concepto);
}
