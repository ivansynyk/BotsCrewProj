package com.dao.impl;

import com.dao.LectorDao;
import com.model.Lector;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class LectorDaoImpl implements LectorDao {
    private Session session;


    public LectorDaoImpl(Session session) {
        this.session = session;
    }

    public List<Lector> findAll() {
        return (List<Lector>) session.createQuery("FROM Department").list();
    }

    public Lector findById(Integer id) {
        return session.get(Lector.class, id);
    }

    public void create(Lector lector) {
        session.getTransaction().begin();
        session.save(lector);
        session.getTransaction().commit();
    }

    public void update(Lector lector) {
        session.getTransaction().begin();
        session.update(lector);
        session.getTransaction().commit();
    }

    public void delete(Lector lector) {
        session.getTransaction().begin();
        session.remove(lector);
        session.getTransaction().commit();
    }

    public List<String> findByChars(String chars) {
        Query query = session.createQuery("SELECT concat( l.name, ' ',l.surName) FROM Lector l " +
                "WHERE l.name  LIKE :chars or  l.surName LIKE :chars ");
        List<String> lectors = query.setParameter("chars", "%" + chars + "%").list();

        return lectors;
    }

    public long countProfessors(String departament) {
        String degree = "Professor";
        Query query = session.createQuery("SELECT COUNT(l.id) FROM Lector l "
                + "Inner join l.departments d Where d.name = :department and l.degree = :degree");
        query.setParameter("department", departament);
        query.setParameter("degree", degree);
        return (long) query.getSingleResult();
    }

    public long countAssistants(String departament) {
        String degree = "Assistant";
        Query query = session.createQuery("SELECT COUNT(l.id) FROM Lector l "
                + "Inner join l.departments d Where d.name = :department and l.degree = :degree");
        query.setParameter("department", departament);
        query.setParameter("degree", degree);
        return (long) query.getSingleResult();
    }

    public long countAssociateProfessors(String departament) {
        String degree = "Associate professor";
        Query query = session.createQuery("SELECT COUNT(l.id) FROM Lector l "
                + "Inner join l.departments d Where d.name = :department and l.degree = :degree");
        query.setParameter("department", departament);
        query.setParameter("degree", degree);
        return (long) query.getSingleResult();
    }


}
