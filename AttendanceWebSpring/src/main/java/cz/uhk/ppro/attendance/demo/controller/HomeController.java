package cz.uhk.ppro.attendance.demo.controller;

import cz.uhk.ppro.attendance.demo.model.Attendance;
import cz.uhk.ppro.attendance.demo.model.Department;
import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.service.AttendanceRepository;
import cz.uhk.ppro.attendance.demo.service.DepartmentRepository;
import cz.uhk.ppro.attendance.demo.service.EmployeeDB;
import cz.uhk.ppro.attendance.demo.service.InitDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {


    @Autowired
    InitDatabaseService initDatabaseService;

    @Autowired
    EmployeeDB employeeDB;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @RequestMapping("/")
    //@ResponseBody
    public String action(){
        initDatabaseService.initDB();



       /* Employee employee = employeeDB.findEmployeeByName("Martin","Chaloupka");
        if (employee == null){
            return "ahoj!";
        }else {
            return employee.getFirst_name() + employee.getLast_name();
        }*/

       System.out.println(String.valueOf(departmentRepository.count()));

       return "redirect:./index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String helloworld(HttpSession session, Model model) {

        if(session.getAttribute("access") == null)
        {
            return "redirect:/login";

        }else {
            Employee employee = employeeDB.findEmployeeByLogin(session.getAttribute("login").toString());

            List<Attendance> attendances = attendanceRepository.findRecentAttendances(employee.getId_employee());

            System.out.println("employee ID" + "" +employee.getId_employee());

            System.out.println("pocet dochazek " + attendances.size());

            model.addAttribute("attendances", attendances);


            return "./home";
        }
    }

    @RequestMapping("/test")
    public String testCSS(){
        System.out.println("test");
        return "./test"; }
}
