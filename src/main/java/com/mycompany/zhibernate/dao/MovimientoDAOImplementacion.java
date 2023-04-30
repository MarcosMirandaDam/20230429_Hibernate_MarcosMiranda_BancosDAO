package com.mycompany.zhibernate.dao;

import com.mycompany.zhibernate.modelo.Movimiento;
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
public class MovimientoDAOImplementacion implements MovimientoDAO{
    
    private SessionFactory sessionFactory;

    public MovimientoDAOImplementacion(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void crear(Movimiento movimiento) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(movimiento);
            transaction.commit();
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error al crear el movimiento: " + e.getMessage());
          
        }
    }

    @Override
    public Movimiento obtenerPorId(Long id) {
        Session session = sessionFactory.openSession();
        Movimiento movimiento = session.get(Movimiento.class, id);
        session.close();
        return movimiento;
    }

    @Override
    public List<Movimiento> obtenerTodos() {
       Session session = sessionFactory.openSession();
        List<Movimiento> listaMovimientos = session.createQuery("from Movimiento", Movimiento.class).list();
        session.close();
        return listaMovimientos;
    }

    @Override
    public void actualizar(Movimiento movimiento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(movimiento);
        transaction.commit();
        session.close();
    }

    @Override
    public void eliminar(Movimiento movimiento) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(movimiento);
        transaction.commit();
        session.close();
    }

    @Override
    public List<Movimiento> findByIdMovimientoContainingIgnoreCase(String idMovimiento) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movimiento> query = session.createQuery("FROM Movimiento WHERE lower(idMovimiento) LIKE :idMovimiento", Movimiento.class);
            query.setParameter("IdMovimiento", "%" + idMovimiento.toLowerCase() + "%");
            return query.list();

            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Movimiento> findByConceptoOrderByCantidadDesc(String concepto) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movimiento> query = session.createQuery("FROM Movimiento WHERE concepto = :concepto ORDER BY cantidad DESC", Movimiento.class);
            query.setParameter("concepto", concepto);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
}
