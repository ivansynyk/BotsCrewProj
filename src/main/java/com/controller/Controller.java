package com.controller;

import com.service.impl.DepartmentServiceImpl;
import com.service.impl.LectorServiceImpl;
import com.util.SessionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Controller {

    private static Logger log = LogManager.getLogger(Controller.class);

    private Scanner scanner;
    private DepartmentServiceImpl departamentService;
    private LectorServiceImpl lectorService;
    private Map<String, String> menu;
    private Map<String, Runnable> methodRun;

    public Controller() {
        departamentService = new DepartmentServiceImpl(SessionUtil.getSession());
        lectorService = new LectorServiceImpl(SessionUtil.getSession());
        scanner = new Scanner(System.in);
        menu = new LinkedHashMap<>();
        methodRun = new LinkedHashMap<>();
        initMenu();
        methodRun.put("1", () -> doCRUDwithLector());
        methodRun.put("2", () -> doCRUDwithDepartment());
        methodRun.put("3", () -> departamentService.showHead(scanner.next()));
        methodRun.put("4", () -> lectorService.showStatistic(scanner.next()));
        methodRun.put("5", () -> departamentService.getAverageSalary(scanner.next()));
        methodRun.put("6", () -> departamentService.countEmployee(scanner.next()));
        methodRun.put("7", () -> lectorService.findByChars(scanner.next()));
        methodRun.put("exit", () -> log.info("Bye"));
    }


    public void doCRUDwithDepartment() {
        System.out.println("1 create \n2 update\n3 delete \n4 find all\n5 find by id");
        switch (scanner.nextInt()) {
            case 1:
                departamentService.create();
                break;
            case 2:
                departamentService.update(scanner.nextInt());
                break;
            case 3:
                departamentService.delete(scanner.nextInt());
                break;
            case 4:
                departamentService.findAll();
                break;
            case 5:
                departamentService.findById(scanner.nextInt());
                break;
        }
    }

    public void doCRUDwithLector() {
        System.out.println("1 create \n2 update\n3 delete \n4 find all\n5 find by id");
        switch (scanner.nextInt()) {
            case 1:
                lectorService.create();
                break;
            case 2:
                lectorService.update(scanner.nextInt());
                break;
            case 3:
                lectorService.delete(scanner.nextInt());
                break;
            case 4:
                lectorService.findAll();
                break;
            case 5:
                lectorService.findById(scanner.nextInt());
                break;
        }
    }

    public void show() {
        String flag = "";
        do {
            menu.values().forEach(s -> log.info(s));
            try {
                flag = scanner.next();
                methodRun.get(flag).run();
            } catch (NullPointerException e) {
                log.warn("Input correct option");
            }
        } while (!flag.equals("exit"));
        scanner.close();
    }

    private void initMenu() {
        menu.put("1", "1 - Lector CRUD");
        menu.put("2", "2 - Department CRUD");
        menu.put("3", "3 - Who is head of department");
        menu.put("4", "4 - Show statistic ");
        menu.put("5", "5 - Show the average salary for department ");
        menu.put("6", "6 - Show count of employee for department ");
        menu.put("7", "7 - Global search ");
        menu.put("exit", "exit - Exit");
    }
}
