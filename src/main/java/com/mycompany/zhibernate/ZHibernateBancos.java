package com.mycompany.zhibernate;

import com.mycompany.zhibernate.dao.ClienteDAOImplementacion;
import com.mycompany.zhibernate.dao.CuentaDAOImplementacion;
import com.mycompany.zhibernate.dao.DireccionDAOImplementacion;
import com.mycompany.zhibernate.dao.MovimientoDAOImplementacion;
import com.mycompany.zhibernate.modelo.Cliente;
import com.mycompany.zhibernate.modelo.Cuenta;
import com.mycompany.zhibernate.modelo.Direccion;
import com.mycompany.zhibernate.modelo.Movimiento;
import com.mycompany.zhibernate.modelo.Sucursal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Marcos Miranad
 */
public class ZHibernateBancos {

    public static void main(String[] args) {
       // Configurar Hibernate
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        
        //creo una instancia de SucursalImp
        Sucursal sucursal1 = new Sucursal(1, 101, 985662735, "santaAna@gmail.com", 985662735, null,null);
        //creo instancia de SucursalDAO
        SucursalDAOImplementacion sucursalDAOImplementacion = new SucursalDAOImplementacion(sessionFactory);
        sucursalDAOImplementacion.crear(sucursal1);
        
        //creo una lista de clientes para posteriormente añadirlos a la sucursal
        Cliente cliente1 = new Cliente(1, "13158801H", "Marcos", "Miranda", sucursal1,null);
        ClienteDAOImplementacion clienteDAOImplementacion = new ClienteDAOImplementacion(sessionFactory);
        clienteDAOImplementacion.crear(cliente1);
        
        //creo una direccion
        Direccion direccion1= new Direccion(1, "La Vega", 33940, "El Entrego", "Asturias");
        DireccionDAOImplementacion direccionDAOImplementacion = new DireccionDAOImplementacion(sessionFactory);
        direccionDAOImplementacion.crear(direccion1);
        //le ponemos la direccion a la sucursal1 que creamos anteriormente
        sucursal1.setDireccion(direccion1);
        //la actualizamos
        sucursalDAOImplementacion.actualizar(sucursal1);
        
        //creo una cuenta corriente
        Cuenta cuenta1 = new Cuenta(1, "ES132090290350000083",25402.25f , "EURO", cliente1,null);
        CuentaDAOImplementacion cuentaDAOImplementacion = new CuentaDAOImplementacion(sessionFactory);
        cuentaDAOImplementacion.crear(cuenta1);
        //le asigno esta cuenta al cliente1 por ejemplo, primero creo la lista de cuentas corrientes y añado la creada
        List<Cuenta> listaCuentasCorrientes = new ArrayList<Cuenta>();
        listaCuentasCorrientes.add(cuenta1);
        cliente1.setListaCuentasCorrientes(listaCuentasCorrientes);          //le asigno la lista de cuentas corrientes
        clienteDAOImplementacion.actualizar(cliente1);                       //actualizo el cliente
        cuentaDAOImplementacion.actualizar(cuenta1);                         //actualizo la cuenta tambien
        
        //creo un movimiento
        Movimiento movimiento1 = new Movimiento(1, 500, 1001, "transferencia",new Date(), cuenta1);
        MovimientoDAOImplementacion movimientoDAOImplementacion = new MovimientoDAOImplementacion(sessionFactory);     
        movimientoDAOImplementacion.crear(movimiento1);
        
        
        
        sessionFactory.close();                 //cerramos la sesion
    }
    
}
