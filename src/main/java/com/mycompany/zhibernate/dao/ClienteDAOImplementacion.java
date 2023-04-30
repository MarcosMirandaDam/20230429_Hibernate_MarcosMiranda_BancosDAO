package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Cliente;
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
public class ClienteDAOImplementacion implements ClienteDAO{
    
    private SessionFactory sessionFactory;
    
    public ClienteDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Cliente cliente) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cliente);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear el cliente: " + e.getMessage());
          
        }
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        Session session = sessionFactory.openSession();
        Cliente cliente = session.get(Cliente.class, id);
        session.close();
        return cliente;
        
    }

    @Override
    public List<Cliente> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Cliente> clientes = session.createQuery("from Cliente", Cliente.class).list();
        session.close();
        return clientes;
    }

    @Override
    public void actualizar(Cliente cliente) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cliente);
        transaction.commit();
        session.close();
        
    }

    @Override
    public void eliminar(Cliente cliente) {
       Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cliente);
        transaction.commit();
        session.close(); 
        
    }

    @Override
    public List<Cliente> findByNombreContainingIgnoreCase(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente WHERE lower(nombre) LIKE :nombre", Cliente.class);
            query.setParameter("nombre", "%" + nombre.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cliente> findByNombreOrderByNombreDesc(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cliente> query = session.createQuery("FROM Cliente WHERE nombre = :nombre ORDER BY numero DESC", Cliente.class);
            query.setParameter("nombre", nombre);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
