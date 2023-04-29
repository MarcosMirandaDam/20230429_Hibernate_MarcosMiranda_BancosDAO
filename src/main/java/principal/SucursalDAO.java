
package principal;

import com.mycompany.zhibernate.modelo.Sucursal;
import java.util.List;

/**
 *
 * @author Marcos Miranda
 */
public interface SucursalDAO {
    
    // Crear una nueva sucursal
    void crear(Sucursal sucursal);

    // Leer una sucursal por ID
    Sucursal obtenerPorId(Long id);

    // Leer todos los sucursales
    List<Sucursal> obtenerTodos();

    // Actualizar un sucursal
    void actualizar(Sucursal sucursal);

    // Eliminar un sucursal
    void eliminar(Sucursal sucursal);

    
    // Buscar sucursales por nombre (ignorando mayúsculas y minúsculas)
    List<Sucursal> findByNombreContainingIgnoreCase(String nombre);

    // Buscar sucursales por nombre y ordenar por numero de sucursal de forma descendente
    List<Sucursal> findByNombreOrderByNumeroDesc(String  nombre);
}


