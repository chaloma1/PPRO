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
import org.springframework.web.bind.annotation.*;
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

       //System.out.println(String.valueOf(departmentRepository.count()));

       return "redirect:./index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String helloworld(HttpSession session, Model model) {
        String access = employeeDB.checkAccess(session);

        if(access.equals("unknown"))
        {
            return "redirect:./login";

        }else {
            Employee employee = employeeDB.findEmployeeByLogin(session.getAttribute("login").toString());

            model.addAttribute("employee", employee);

            List<Attendance> attendances = attendanceRepository.findRecentAttendances(employee.getId_employee());

            System.out.println("employee ID" + "" +employee.getId_employee());

            System.out.println("pocet dochazek " + attendances.size());

            model.addAttribute("attendances", attendances);

            List<Employee> employees = employeeDB.findAllEmployee();
            System.out.println("employees size" + employees.size());

            if (employeeDB.isDepartmentSupervisor(employee.getId_employee()) == true){
                try{

                    List<Employee> members = employeeDB.findMembersFromDepartment(
                            employee.getId_employee(),
                            departmentRepository.findBySupervisor(employee.getLogin_name()).getId_department());

                    model.addAttribute("members", members);

                }catch (Exception e){
                    System.out.println("chyba v nacteni uzivatelu supervisora");
                    return "./home";
                }






            }




            return "./home";
        }
    }

    @RequestMapping(value = "/provideDetailUser", method = RequestMethod.GET)
    public String provideDetailUser(@RequestParam(required = false) String userLogin , HttpSession session,Model model, RedirectAttributes messages){

        String access = employeeDB.checkAccess(session);
        if (access.equals("admin") || access.equals("employee")){

            Employee e = null;


            try {
                if (userLogin == null) {
                    e = employeeDB.findEmployeeByLogin(session.getAttribute("login").toString());
                } else if (employeeDB.isValidUser(userLogin) != false) {
                    e = employeeDB.findEmployeeByLogin(userLogin);
                } else {
                    messages.addFlashAttribute("failuremessage", "uzivatel neni v DB");
                    return "redirect:./index";
                }

                model.addAttribute("employee", e);
                List<Attendance> attendances = attendanceRepository.findRecentAttendances(e.getId_employee());

                model.addAttribute("attendances", attendances);


            }catch (Exception exception){
                messages.addFlashAttribute("failuremessage", "Uzivatel nesel nacist v detailu" + " " + exception.getMessage());
                return "redirect:./index";
            }



        }else if (access.equals("unknown")){
            return "redirect:./login";
        }else { return "redirect:./login";}


        return "./userDetail";

    }

  /*  @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public String userDetail(HttpSession session){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){


        }else if (access.equals("unknown")){
            return "redirect:./login";
        }

        return "./userDetail";

    }*/

    @RequestMapping("/test")
    public String testCSS(){
        System.out.println("test");
        return "./test"; }
}
