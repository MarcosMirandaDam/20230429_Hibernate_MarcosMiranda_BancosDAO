package com.mycompany.zhibernate;

import com.mycompany.zhibernate.dao.ClienteDAOImplementacion;
import com.mycompany.zhibernate.modelo.Cliente;
import com.mycompany.zhibernate.modelo.Sucursal;
import java.util.ArrayList;
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
        Sucursal sucursal1 = new Sucursal(1, 101, 985662735, "santaAna@gmail.com", 985662735, null);
        //creo instancia de SucursalDAO
        SucursalDAOImplementacion sucursalDAOImplementacion = new SucursalDAOImplementacion(sessionFactory);
        sucursalDAOImplementacion.crear(sucursal1);
        
        //creo una lista de clientes para posteriormente añadirlos a la sucursal
        Cliente cliente1 = new Cliente(1, "13158801H", "Marcos", "Miranda", sucursal1);
        ClienteDAOImplementacion clienteDAOImplementacion = new ClienteDAOImplementacion(sessionFactory);
        clienteDAOImplementacion.crear(cliente1);
        
        
        
        
        
        sessionFactory.close();                 //cerramos la sesion
    }
    
}
