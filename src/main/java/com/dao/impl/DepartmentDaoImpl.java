package com.dao.impl;

import com.dao.DepartmentDao;
import com.model.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {
    private Session session;

    public DepartmentDaoImpl(Session session) {
        this.session = session;
    }

    public List<Department> findAll() {
        return (List<Department>) session.createQuery("FROM Department").list();
    }

    public Department findById(Integer id) {
        return session.get(Department.class, id);
    }

    public void create(Department department) {
        session.getTransaction().begin();
        session.save(department);
        session.getTransaction().commit();
    }

    public void update(Department department) {
        session.getTransaction().begin();
        session.update(department);
        session.getTransaction().commit();
    }

    public void delete(Department department) {
        session.getTransaction().begin();
        session.remove(department);
        session.getTransaction().commit();
    }

    public double getAverageSalary(String department) {
        Query query = session.createQuery("SELECT avg(l.salary) FROM Lector l" +
                " JOIN l.departments d WHERE d.name =:department");
        query.setParameter("department", department);
        return (double) query.getSingleResult();
    }

    public long countEmployee(String department) {
        Query query = session.createQuery("Select count(l.id) FROM Lector as l " +
                "Inner join l.departments d Where d.name = :department");
        query.setParameter("department", department);
        return (long) query.getSingleResult();
    }

    public String showHead(String department) {
        Query query = session.createQuery(" SELECT D.head FROM Department D WHERE D.name = :department");
        query.setParameter("department", department);
        return (String) query.getSingleResult();
    }
}
