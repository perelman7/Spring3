package com.webTest;

import com.webTest.config.HibernateAnnotationConfig;
import com.webTest.models.Department;
import com.webTest.models.Employee;
import com.webTest.services.IDepartmentService;
import com.webTest.services.IEmployeeService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    private static Log log = LogFactory.getLog(Main.class);

    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(HibernateAnnotationConfig.class);
        IDepartmentService deps = context.getBean("departmentService", IDepartmentService.class);
        IEmployeeService emps = context.getBean("employeeService", IEmployeeService.class);

        int id = emps.save(new Employee("qqq"+23, "www"+23, "eee"+23, "1999-01-02", new Department(10, "IT"+9, "IT"+9)));
        System.out.println("id: " + id);

        System.out.println("*******************************************************");
        List<Employee> employees = emps.findAll();
        employees.forEach(e -> System.out.println(e));
    }

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.test();
    }
}
