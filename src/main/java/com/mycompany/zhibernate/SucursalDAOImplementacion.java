package com.mycompany.zhibernate;

import com.mycompany.zhibernate.modelo.Sucursal;
import com.mycompany.zhibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Marcos Miranda
 */
public class SucursalDAOImplementacion implements SucursalDAO {
    
    private SessionFactory sessionFactory;

    public SucursalDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Sucursal sucursal) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(sucursal);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la sucursal: " + e.getMessage());
          
        }
    }

    @Override
    public Sucursal obtenerPorId(Long id) {
        Session session = sessionFactory.openSession();
        Sucursal sucursal = session.get(Sucursal.class, id);
        session.close();
        return sucursal;
    }

    @Override
    public List<Sucursal> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Sucursal> sucursales = session.createQuery("from Sucursal", Sucursal.class).list();
        session.close();
        return sucursales;
    }

    @Override
    public void actualizar(Sucursal sucursal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(sucursal);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Sucursal sucursal) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(sucursal);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Sucursal> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Sucursal> query = session.createQuery("FROM Sucursal WHERE lower(nombre) LIKE :nombre", Sucursal.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Sucursal> findByNombreOrderByNumeroDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Sucursal> query = session.createQuery("FROM Sucursal WHERE nombre = :nombre ORDER BY numero DESC", Sucursal.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    
    
}
