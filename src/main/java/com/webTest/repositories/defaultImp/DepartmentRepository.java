package com.webTest.repositories.defaultImp;

import com.webTest.models.Department;
import com.webTest.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("departmentRepository")
public class DepartmentRepository extends CrudRepository<Department> {

    public DepartmentRepository(){
        setClazz(Department.class);
    }
}
