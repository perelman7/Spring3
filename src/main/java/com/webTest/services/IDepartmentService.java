package com.webTest.services;

import com.webTest.models.Department;

import java.util.List;

public interface IDepartmentService {

    Department findOne(int id);
    List<Department> findAll();
    int save(Department entity);
    void update(Department entity);
    void deleteById(int id);
    void delete(Department entity);
}
