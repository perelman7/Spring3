package com.webTest.services.crudImp;

import com.webTest.models.Department;
import com.webTest.repositories.ICrudRepository;
import com.webTest.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentService implements IDepartmentService {

    @Autowired
    @Qualifier("departmentRepository")
    ICrudRepository repository;

    @Override
    public Department findOne(int id) {
        return (Department) repository.findOne(id);
    }

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public int save(Department entity) {
        return repository.save(entity);
    }

    @Override
    public void update(Department entity) {
        repository.update(entity);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Department entity) {
        repository.delete(entity);
    }
}
