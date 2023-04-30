package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Cuenta;
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
public class CuentaDAOImplementacion implements CuentaDAO{
    
    private SessionFactory sessionFactory;

    public CuentaDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Cuenta cuenta) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cuenta);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear la cuenta corriente: " + e.getMessage());
          
        }
    }

    @Override
    public Cuenta obtenerPorId(Long id) {
        Session session = sessionFactory.openSession();
        Cuenta cuenta = session.get(Cuenta.class, id);
        session.close();
        return cuenta;
    }

    @Override
    public List<Cuenta> obtenerTodos() {
        Session session = sessionFactory.openSession();
        List<Cuenta> cuentasCorrientes = session.createQuery("from Sucursal", Cuenta.class).list();
        session.close();
        return cuentasCorrientes;
    }

    @Override
    public void actualizar(Cuenta cuenta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cuenta);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Cuenta cuenta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cuenta);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Cuenta> findByNumeroCuentaContainingIgnoreCase(String numeroCC) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cuenta> query = session.createQuery("FROM Cuenta WHERE lower(numeroCC) LIKE :numeroCC", Cuenta.class);
            query.setParameter("nombre", "%" + numeroCC.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Cuenta> findByDivisaOrderBySaldoDesc(String divisa) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cuenta> query = session.createQuery("FROM Cuenta WHERE divisa = :divisa ORDER BY saldo DESC", Cuenta.class);
            query.setParameter("divisa", divisa);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    
}
