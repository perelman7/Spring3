package com.webTest.repositories;

import java.util.List;

public interface ICrudRepository<T> {

    T findOne(int id);
    List<T> findAll();
    int save(T entity);
    void update(T entity);
    void deleteById(int id);
    void delete(T entity);
}
