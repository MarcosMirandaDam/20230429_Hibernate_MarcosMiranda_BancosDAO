package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Cuenta;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface CuentaDAO {
    
    // Crear una nueva cuenta corriente
    void crear(Cuenta cuenta);

    // Leer una cuenta corriente por ID
    Cuenta obtenerPorId(Long id);

    // Leer todas las cuentas corrientes
    List<Cuenta> obtenerTodos();

    // Actualizar una cuenta corriente
    void actualizar(Cuenta cuenta);

    // Eliminar una cuenta corriente
    void eliminar(Cuenta cuenta);

    
    // Buscar cuentas corrientes por numero de cuenta (ignorando mayúsculas y minúsculas)
    List<Cuenta> findByNumeroCuentaContainingIgnoreCase(String numeroCC);

    // Buscar cuentas corrientes por divisa y ordenar por saldo de forma descendente
    List<Cuenta> findByDivisaOrderBySaldoDesc(String  divisa);
}
