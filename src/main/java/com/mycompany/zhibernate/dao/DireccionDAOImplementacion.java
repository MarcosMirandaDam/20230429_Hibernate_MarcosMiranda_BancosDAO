
package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Cliente;
import com.mycompany.zhibernate.modelo.Direccion;
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
public class DireccionDAOImplementacion implements DireccionDAO{
    
    private SessionFactory sessionFactory;
    
    public DireccionDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Direccion direccion) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(direccion);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la direccion: " + e.getMessage());
          
        }
    }

    @Override
    public Direccion obtenerPorId(Long id) {
        Session session = sessionFactory.openSession();
        Direccion direccion = session.get(Direccion.class, id);
        session.close();
        return direccion;
    }

    @Override
    public List<Direccion> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Direccion> direcciones = session.createQuery("from Cliente", Direccion.class).list();
        session.close();
        return direcciones;
    }

    @Override
    public void actualizar(Direccion direccion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(direccion);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Direccion direccion) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(direccion);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Direccion> findByCiudadContainingIgnoreCase(String ciudad) {
       try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Direccion> query = session.createQuery("FROM Direccion WHERE lower(ciudad) LIKE :ciudad", Direccion.class);
            query.setParameter("ciudad", "%" + ciudad.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Direccion> findByCiudadOrderByCpDesc(String ciudad) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Direccion> query = session.createQuery("FROM Direccion WHERE ciudad = :ciudad ORDER BY cp DESC", Direccion.class);
            query.setParameter("nombre", ciudad);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }
    
}
