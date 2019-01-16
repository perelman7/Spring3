package com.webTest.repositories.defaultImp;

import com.webTest.models.Employee;
import com.webTest.repositories.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("employeesRepository")
public class EmployeeRepository extends CrudRepository<Employee> {

    public EmployeeRepository(){
        setClazz(Employee.class);
    }
}
