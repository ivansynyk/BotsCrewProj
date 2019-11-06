package com.dao;

import java.util.List;

public interface GeneralDao<T, Integer> {
    List<T> findAll();

    T findById(Integer id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
