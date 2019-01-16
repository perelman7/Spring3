package com.webTest.controller;

import com.webTest.models.Department;
import com.webTest.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/deps")
public class DepartmentController {

    @Autowired
    @Qualifier("departmentService")
    private IDepartmentService service;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Department> allDepartments(){
        return service.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public int addDepartment(HttpServletRequest request){
        Department department = this.getDepartment(request);
        return service.save(department);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public Department updateDepartment(HttpServletRequest request){
        Department department = this.getDepartment(request);
        department.setId(Integer.parseInt(request.getParameter("id")));
        service.update(department);
        return department;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteDepartment(HttpServletRequest request){
        service.deleteById(Integer.parseInt(request.getParameter("id")));
    }

    private Department getDepartment(HttpServletRequest request){
        return new Department(
                request.getParameter("depName"),
                request.getParameter("description")
        );
    }
}