package com.webTest.services;

import com.webTest.models.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee findOne(int id);
    List<Employee> findAll();
    int save(Employee entity);
    void update(Employee entity);
    void deleteById(int id);
    void delete(Employee entity);
}
