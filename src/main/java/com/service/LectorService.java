package com.service;

public interface LectorService {
    void findAll();

    void findById(Integer id);

    void create();

    void update(Integer id);

    void delete(Integer id);

    void findByChars(String chars);

    void showStatistic(String department);
}
