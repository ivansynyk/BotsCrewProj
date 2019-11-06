package com.service.impl;

import com.dao.impl.LectorDaoImpl;
import com.model.Lector;
import com.service.LectorService;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class LectorServiceImpl implements LectorService {

    private Scanner scanner;
    private Session session;
    private LectorDaoImpl lectorDao;

    public LectorServiceImpl(Session session) {
        scanner = new Scanner(System.in);
        this.session = session;
        lectorDao = new LectorDaoImpl(session);
    }

    @Override
    public void findAll() {
        lectorDao.findAll().forEach(System.out::println);
    }

    @Override
    public void findById(Integer id) {
        System.out.println(lectorDao.findById(id));
    }

    @Override
    public void create() {
        Lector lector = new Lector();
        System.out.println("Name :");
        lector.setName(scanner.next());
        System.out.println("Surname :");
        lector.setSurName(scanner.next());
        System.out.println("Degree");
        lector.setDegree(scanner.next());
        System.out.println("Salary");
        lector.setSalary(scanner.nextInt());
        lectorDao.create(lector);
    }

    @Override
    public void update(Integer id) {
        Lector lector = lectorDao.findById(id);
        System.out.println("Name :");
        lector.setName(scanner.next());
        System.out.println("Surname :");
        lector.setSurName(scanner.next());
        System.out.println("Degree");
        lector.setDegree(scanner.next());
        System.out.println("Salary");
        lector.setSalary(scanner.nextInt());
        lectorDao.update(lector);
    }

    @Override
    public void delete(Integer id) {
        lectorDao.delete(lectorDao.findById(id));
    }

    @Override
    public void findByChars(String chars) {
        List<String> lectors = lectorDao.findByChars(chars);
        lectors.forEach(System.out::println);
    }

    public void showStatistic(String department) {
        long countAssistants = lectorDao.countAssistants(department);
        long countAssociateProfressors = lectorDao.countAssociateProfessors(department);
        long countProfessors = lectorDao.countProfessors(department);
        String statistic = "assistants - " + countAssistants +
                "\nassociate professors - " + countAssociateProfressors +
                "\nprofessors - " + countProfessors;
        System.out.println(statistic);
    }
}

