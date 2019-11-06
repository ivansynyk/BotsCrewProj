package com.service;

public interface DepartmentService {
    void findAll();

    void findById(Integer id);

    void create();

    void update(Integer id);

    void delete(Integer id);

    void showHead(String departament);

    void countEmployee(String departament);

    void getAverageSalary(String departament);

}
