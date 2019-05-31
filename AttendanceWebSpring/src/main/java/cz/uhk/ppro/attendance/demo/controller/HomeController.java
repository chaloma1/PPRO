package cz.uhk.ppro.attendance.demo.controller;

import cz.uhk.ppro.attendance.demo.model.Department;
import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.service.DepartmentRepository;
import cz.uhk.ppro.attendance.demo.service.EmployeeDB;
import cz.uhk.ppro.attendance.demo.service.InitDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    InitDatabaseService initDatabaseService;

    @Autowired
    EmployeeDB employeeDB;

    @Autowired
    DepartmentRepository departmentRepository;

    @RequestMapping("/")
    @ResponseBody
    public String action(){
        initDatabaseService.initDB();



       /* Employee employee = employeeDB.findEmployeeByName("Martin","Chaloupka");
        if (employee == null){
            return "ahoj!";
        }else {
            return employee.getFirst_name() + employee.getLast_name();
        }*/
       return String.valueOf(departmentRepository.count());
    }

    @RequestMapping("/index")
    public String helloworld() {
        return "home";
    }

    @RequestMapping("/test")
    public String testCSS(){ return "test"; }
}
