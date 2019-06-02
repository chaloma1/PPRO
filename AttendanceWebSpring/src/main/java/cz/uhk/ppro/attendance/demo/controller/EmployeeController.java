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
import java.util.ArrayList;
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
        String access = employeeDB.checkAccess(session);
       if(access.equals("admin")){
           List<Department> departments = departmentRepository.findAll();
           model.addAttribute("departments", departments);



       }else if(access.equals("unknown")){
           return "redirect:.login";
       }

        return "form/employeeForm";

    }

    @RequestMapping(value = "/ulozZamestnance", method = RequestMethod.POST)
    public String ulozZamestnance(@RequestParam String email, @RequestParam String fname,
                                  @RequestParam String lname, @RequestParam String position,
                                  @RequestParam String telephone, @RequestParam String heslo,
                                  @RequestParam int access_level, @RequestParam String login,
                                  @RequestParam String department,
                                  RedirectAttributes messages, HttpSession session){
        String access = employeeDB.checkAccess(session);
        if(access.equals("admin")) {

            try {
                Department d = departmentRepository.findByTitle(department);

                if (employeeDB.findEmployeeByLogin(login) != null){
                    messages.addFlashAttribute("failuremessage", "Login je jiz pouzity.");
                    return "redirect:./index";
                }

                Employee e = new Employee(fname, lname, telephone, email, position, heslo, login, access_level);



                e.setDepartment(d);
                System.out.println(
                        "fname: " + e.getFirst_name() + " "
                                + "lname " + e.getLast_name() + " " + "telephone "
                                + e.getTel_number() + " " + "heslo " + e.getHeslo() + " " + "login " + e.getLogin_name() + " " + "access " + e.getAccess_level());


                employeeDB.createEmployee(e);
                messages.addFlashAttribute("successmessage", "Zamestnanec uspesne vytvoren.");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                return "redirect:./index";
            }

        }else if (access.equals("unknown")){

            return "redirect:./login";

        }

        return "redirect:./index";





    }





    @RequestMapping(value = "/addAttendance", method = RequestMethod.GET)
    public String addAttendance(HttpSession session, Model model)
    {
        String access = employeeDB.checkAccess(session);
            if (access.equals("admin")) {
                employees = employeeDB.findAllEmployee();
                model.addAttribute("employees", employees);
                System.out.println(employees.size() + employees.get(0).getLogin_name());
                model.addAttribute("datum", new Date());




            } else if(access.equals("unknown")) {
                return "redirect:./login";
            }

        return "form/attendanceForm";

    }

    @RequestMapping(value = "/sendAttendance", method = RequestMethod.POST)
    public String sendAttendance(@RequestParam String employee,
                                 @RequestParam String day,
                                 @RequestParam String attend_time,
                                 @RequestParam String leave_time, RedirectAttributes messages, HttpSession session){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm");
            try {
                Date datum_prichodu = simpleDateFormat.parse(day + " " + attend_time);
                Date datum_odchodu = simpleDateFormat.parse(day + " " + leave_time);


                Employee emp = employeeDB.findEmployeeByLogin(employee);


                Attendance a = new Attendance(emp, datum_prichodu, datum_odchodu);


                attendanceRepository.save(a);


                messages.addFlashAttribute("successmessage", "Dochazka uspesne ulozena.");


            } catch (ParseException e) {

                messages.addFlashAttribute("failuremessage", e.getMessage());
            }



        } else if (access.equals("unknown")){
            return "redirect:./login";
        }

        return "redirect:./index";





    }

    @RequestMapping(value = "/addDepartment", method = RequestMethod.GET)
    public String addDepartment(HttpSession session, Model model){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){

        List<Employee> supervisors = employeeDB.findAllEmployee();

        model.addAttribute("supervisors", supervisors);

        }else if (access.equals("unknown")){
            return "redirect:./login";
        }
        return "form/departmentForm";


    }

    @RequestMapping(value = "/sendDepartment", method = RequestMethod.POST)
    public String sendDepartment(@RequestParam String title, @RequestParam String supervisor, HttpSession session, RedirectAttributes messages){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){
            try {

                Department d = new Department(title);
                d.setSupervisor(supervisor.substring(supervisor.indexOf("(")+1, supervisor.indexOf(")")));

                departmentRepository.save(d);

                messages.addFlashAttribute("successmessage", "Oddeleni uspesne ulozeno.");





            }catch (Exception e){

                messages.addFlashAttribute("failuremessage", e.getMessage());
            }

        }else if (access.equals("unknown")){
            return "redirect:./login";
        }
        return "redirect:./index";

    }
    @RequestMapping(value = "/alterDepartment", method = RequestMethod.GET)
    public String alterDepartment(HttpSession session, Model model){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){

            model.addAttribute("uprava", true);

            List<Department> departments = departmentRepository.findAll();
            List<Employee> supervisors = employeeDB.findAllEmployee();

            model.addAttribute("departments", departments);
            model.addAttribute("supervisors", supervisors);

        }else if (access.equals("unknown")){
            return "redirect:./login";
        }


        return "form/departmentForm";


    }

    @RequestMapping(value = "/sendNewDepartment", method = RequestMethod.POST)
    public String sendNewDepartment(@RequestParam String department, @RequestParam String title, @RequestParam String supervisor){

        System.out.println(department);
        System.out.println(title);
        System.out.println(supervisor);


        return "redirect:./index";
    }


}
