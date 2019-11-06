package com.service.impl;

import com.dao.impl.DepartmentDaoImpl;
import com.model.Department;
import com.service.DepartmentService;
import org.hibernate.Session;

import java.util.Scanner;

public class DepartmentServiceImpl implements DepartmentService {

    private Scanner scanner;
    private Session session;
    private DepartmentDaoImpl departamentDao;

    public DepartmentServiceImpl(Session session) {
        scanner = new Scanner(System.in);
        this.session = session;
        departamentDao = new DepartmentDaoImpl(session);
    }

    @Override
    public void findAll() {
        departamentDao.findAll().forEach(System.out::println);
    }

    @Override
    public void findById(Integer id) {
        System.out.println(departamentDao.findById(id));
    }

    @Override
    public void create() {
        Department department = new Department();
        System.out.println("Name of Department:");
        department.setName(scanner.next());
        System.out.println("Head of Department");
        department.setHead(scanner.next());
        departamentDao.create(department);
    }

    @Override
    public void update(Integer id) {
        Department department = departamentDao.findById(id);
        System.out.println("Name of Department:");
        department.setName(scanner.next());
        System.out.println("Head of Department");
        department.setHead(scanner.next());
        departamentDao.update(department);
    }

    @Override
    public void delete(Integer id) {
        departamentDao.delete(departamentDao.findById(id));
    }

    @Override
    public void showHead(String departament) {
        System.out.println("Head of " + departament + " department is " + departamentDao.showHead(departament));
    }

    @Override
    public void countEmployee(String departament) {
        System.out.println(departamentDao.countEmployee(departament));
    }

    @Override
    public void getAverageSalary(String departament) {
        System.out.println(departamentDao.getAverageSalary(departament));
    }
}
