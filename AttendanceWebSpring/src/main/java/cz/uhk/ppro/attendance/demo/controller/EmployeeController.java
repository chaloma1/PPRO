package cz.uhk.ppro.attendance.demo.controller;


import cz.uhk.ppro.attendance.demo.model.Attendance;
import cz.uhk.ppro.attendance.demo.model.Department;
import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.service.AttendanceRepository;
import cz.uhk.ppro.attendance.demo.service.DepartmentRepository;
import cz.uhk.ppro.attendance.demo.service.EmployeeDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDB employeeDB;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    List<Employee> employees;


    @RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
    public String addEmployee(HttpSession session, Model model){
       if(checkAccess(session).equals("admin")){
           List<Department> departments = departmentRepository.findAll();
           model.addAttribute("departments", departments);

           return "form/employeeForm";

       };

       return "redirect:./login";

    }

    @RequestMapping(value = "/ulozZamestnance", method = RequestMethod.POST)
    public String ulozZamestnance(@RequestParam String email, @RequestParam String fname,
                                  @RequestParam String lname, @RequestParam String position,
                                  @RequestParam String telephone, @RequestParam String heslo,
                                  @RequestParam int access_level, @RequestParam String login,
                                  @RequestParam String department,
                                  RedirectAttributes messages){


        try {
        Department d = departmentRepository.findByTitle(department);

        Employee e = new Employee(fname,lname,telephone,email,position,heslo,login,access_level );
        e.setDepartment(d);
        System.out.println(
                "fname: " + e.getFirst_name() + " "
                + "lname " + e.getLast_name() + " " + "telephone "
                        + e.getTel_number() + " " + "heslo " + e.getHeslo() + " " + "login " + e.getLogin_name() + " " + "access " + e.getAccess_level());


            employeeDB.createEmployee(e);
            messages.addFlashAttribute("successmessage", "Zamestnanec uspesne vytvoren.");
        }catch (Exception exception)
        {
            messages.addFlashAttribute("failuremessage",  exception.getMessage());
        }



        return "redirect:./index";

    }





    @RequestMapping(value = "/addAttendance", method = RequestMethod.GET)
    public String addAttendance(HttpSession session, Model model)
    {
        if (session.getAttribute("access") != null) {
            if ((int) session.getAttribute("access") == 0) {
                employees = employeeDB.findAllEmployee();
                model.addAttribute("employees", employees);
                System.out.println(employees.size() + employees.get(0).getLogin_name());
                model.addAttribute("datum", new Date());



                return "form/attendanceForm";
            } else {
                return "redirect:./login";
            }
        }else
            {
                return "redirect:./login";
            }
    }

    @RequestMapping(value = "/sendAttendance", method = RequestMethod.POST)
    public String sendAttendance(@RequestParam String employee,
                                 @RequestParam String day,
                                 @RequestParam String attend_time,
                                 @RequestParam String leave_time, RedirectAttributes messages){

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm");
        try {
            Date datum_prichodu = simpleDateFormat.parse(day + " " + attend_time);
            Date datum_odchodu = simpleDateFormat.parse(day + " " + leave_time);




            Employee emp = employeeDB.findEmployeeByLogin(employee);


            Attendance a = new Attendance(emp,  datum_prichodu, datum_odchodu);



            attendanceRepository.save(a);





            messages.addFlashAttribute("successmessage", "Dochazka uspesne ulozena.");




        } catch (ParseException e) {

            messages.addFlashAttribute("failuremessage", e.getMessage());
        }



        return "redirect:./index";

    }

    public String checkAccess(HttpSession session){
        if(session.getAttribute("access") != null){
            String access_level;
            switch ((int)session.getAttribute("access"))
            {
                case 0: access_level = "admin";break;
                case 1: access_level = "employee";break;
                default: access_level = "redirect:./login";
            }

            return access_level;


        }else
        {
            return "redirect:./login";
        }

    }
}
