package com.webTest.services.crudImp;

import com.webTest.models.Employee;
import com.webTest.repositories.ICrudRepository;
import com.webTest.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("employeeService")
public class EmployeeService implements IEmployeeService {

    @Autowired
    @Qualifier("employeesRepository")
    ICrudRepository repository;

    @Override
    public Employee findOne(int id) {
        return (Employee) repository.findOne(id);
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public int save(Employee entity) {
        return repository.save(entity);
    }

    @Override
    public void update(Employee entity) {
        repository.update(entity);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Employee entity) {
        repository.delete(entity);
    }
}
