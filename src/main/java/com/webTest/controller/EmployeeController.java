package com.webTest.controller;

import com.webTest.models.Department;
import com.webTest.models.Employee;
import com.webTest.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/emps")
public class EmployeeController {

    @Autowired
    @Qualifier("employeeService")
    private IEmployeeService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Employee> allEmployees(){
        return service.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int addEmployee(HttpServletRequest request){
        Employee employee = this.getEmployee(request);
        return service.save(employee);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Employee updateEmployee(HttpServletRequest request){
        Employee employee = this.getEmployee(request);
        employee.setId(Integer.parseInt(request.getParameter("id")));
        service.update(employee);
        return employee;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteEmployee(HttpServletRequest request){
        service.deleteById(Integer.parseInt(request.getParameter("id")));
    }

    private Employee getEmployee(HttpServletRequest request){
        return new Employee(
                request.getParameter("surname"),
                request.getParameter("name"),
                request.getParameter("fatherName"),
                request.getParameter("dob"),
                new Department(
                        Integer.parseInt(request.getParameter("dep_id")),
                        request.getParameter("depName"),
                        request.getParameter("description")
                )
        );
    }
}
