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
import javax.transaction.Transactional;
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
           return "redirect:./login";
       }else { return "redirect:./login";}

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

            if (employeeDB.checkEmployeeLogin(login) == false){
                messages.addFlashAttribute("failuremessage", "Login je jiz pouzity.");
                return "redirect:./index";
            }


            try {

                Department d = departmentRepository.findByTitle(department);

                departmentRepository.save(d);
                System.out.println("Dobre 1");

                Employee e = new Employee(fname, lname, telephone, email, position, heslo, login, access_level);

                System.out.println("model employee");

                e.setDepartment(d);
                System.out.println(
                        "fname: " + e.getFirst_name() + " "
                                + "lname " + e.getLast_name() + " " + "telephone "
                                + e.getTel_number() + " " + "heslo " + e.getHeslo() + " " + "login " + e.getLogin_name() + " " + "access " + e.getAccess_level());

                System.out.println("create?");
                employeeDB.createEmployee(e);
                messages.addFlashAttribute("successmessage", "Zamestnanec uspesne vytvoren.");
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                messages.addFlashAttribute("failuremessage", "Zamestnanec neulozen.");
                return "redirect:./index";
            }

        }else if (access.equals("unknown")){

            return "redirect:./login";

        }else { return "redirect:./login";}

        return "redirect:./index";





    }

    @RequestMapping(value = "/editUserAttendance", method = RequestMethod.GET)
    public String editUserAttendance(@RequestParam int id_attendance, HttpSession session, Model model, RedirectAttributes messages){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")) {

            try {
                Attendance a = attendanceRepository.getOne(id_attendance);
                model.addAttribute("attendance", a);
            }catch (Exception e){
                messages.addFlashAttribute("failuremessage", "nepodarilo se nahrat dochazku k upraveni.");

            }




        } else if(access.equals("unknown")) {
            return "redirect:./login";
        }else { return "redirect:./login";}

        return "form/alterAttendance";
        //return "form/alterAttendanceForm";

    }

    @RequestMapping(value = "/alterAttendance", method = RequestMethod.POST)
    public String alterAttendance(@RequestParam int id_attendance, @RequestParam String attend_time,
                                  @RequestParam String leave_time, @RequestParam(required = false) Boolean toDelete,
                                  HttpSession session, Model model, RedirectAttributes messages){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")) {


            if (toDelete != null && toDelete == true){
                try{
                    Attendance a = attendanceRepository.getOne(id_attendance);
                    attendanceRepository.delete(a);
                    messages.addFlashAttribute("successmessage", "Dochazka vymazana.");
                    return "redirect:./index";

                }catch (Exception e){
                    messages.addFlashAttribute("failuremessage", "Dochazka nesla smazat.");
                    return "redirect:./index";
                }
            }else {
                try{

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
                    simpleDateFormat.applyPattern("yyyy-MM-dd HH:mm");

                    Date attend = simpleDateFormat.parse(attend_time);

                    Date leave = simpleDateFormat.parse(leave_time);


                    int milli_to_hour = 1000 * 60* 60;
                    if ((int)(leave.getTime() - attend.getTime()) / milli_to_hour <= 0){
                        messages.addFlashAttribute("failuremessage", "Nedostatecny nebo spatne upraveny cas dochazky.");

                        try {
                            Attendance a = attendanceRepository.getOne(id_attendance);
                            model.addAttribute("attendance", a);
                        }catch (Exception e){
                            messages.addFlashAttribute("failuremessage", "Neco se pokazilo ve zpracovani formulare pro upravu dochazky" + e.getMessage());
                            return "redirect:./index";
                        }

                        return "form/alterAttendance";


                    }

                    attendanceRepository.alterAttendance(id_attendance, attend, leave);

                    messages.addFlashAttribute("successmessage", "Dochazka upravena");

                }catch (Exception e){

                    messages.addFlashAttribute("failuremessage", "Chyba v uprave dochazky." + e.getMessage());
                    return "redirect:./index";

                }

                return "redirect:./index";
            }





        } else if(access.equals("unknown")) {
            return "redirect:./login";
        }else { return "redirect:./login";}

        /* novy! return "redirect:./index"; */
        //return "form/alterAttendanceForm";

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
            }else { return "redirect:./login";}

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
        }else { return "redirect:./login";}

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
        }else { return "redirect:./login";}
        return "form/departmentForm";


    }

    @RequestMapping(value = "/sendDepartment", method = RequestMethod.POST)
    public String sendDepartment(@RequestParam String title, @RequestParam String supervisor, HttpSession session, RedirectAttributes messages){
        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){
            try {
                String sv = null;
                if (supervisor != null && supervisor.isEmpty() == false) {
                    sv = supervisor.substring(supervisor.indexOf("(") + 1, supervisor.indexOf(")"));
                }

                if (sv != null && employeeDB.isDepartmentSupervisor(sv) == true){
                    messages.addFlashAttribute("failuremessage", "Tento zamestnanec jiz je supervisorem v nejakem oddeleni.");
                    return "redirect:./index";
                }

                if (title.isEmpty() == true || title == null || departmentRepository.checkDepartmentTitle(title.trim()).size() >= 1){
                    messages.addFlashAttribute("failuremessage", "Kolonka oddeleni by nemelo byt prazdne nebo jiz pouzito v databazi.");
                    return "redirect:./index";
                }

                Department d = new Department(title.trim(), sv);
                //d.setSupervisor(supervisor.substring(supervisor.indexOf("(")+1, supervisor.indexOf(")")));

                departmentRepository.save(d);

                messages.addFlashAttribute("successmessage", "Oddeleni uspesne ulozeno.");





            }catch (Exception e){

                messages.addFlashAttribute("failuremessage", "stala se chyba" + "" +  e.getMessage());
            }

        }else if (access.equals("unknown")){
            return "redirect:./login";
        }else { return "redirect:./login";}
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
        }else { return "redirect:./login";}


        return "form/departmentForm";


    }

    @RequestMapping(value = "/sendNewDepartment", method = RequestMethod.POST)
    public String sendNewDepartment(@RequestParam String department, String title, String supervisor,
                                    HttpSession session, RedirectAttributes messages){

        String access = employeeDB.checkAccess(session);
        if (access.equals("admin")){

            String oldTitle = null;
            String oldSupervisor = null;

            String newSupervisor = null;

            try {

               oldTitle = department.substring(department.indexOf(""), department.indexOf(" Vedouci:"));

               oldSupervisor = department.substring(department.indexOf("(")+1, department.indexOf(")") );

               newSupervisor = supervisor.substring(supervisor.indexOf("(")+1, supervisor.indexOf(")"));

               if (employeeDB.isDepartmentSupervisor(newSupervisor) == true){
                   messages.addFlashAttribute("failuremessage", "Tento zamestnanec jiz je supervisorem v nejakem oddeleni.");
                   return "redirect:./index";
               }

            }catch (Exception e){
                System.out.println("Nepodarilo se rozparsovat string pro upravu oddeleni.");
            }

            try {

                Department d = departmentRepository.findByTitle(oldTitle);

                if (departmentRepository.checkDepartmentTitle(title.trim()).size() >= 1){
                    messages.addFlashAttribute("failuremessage", "Nelze pridat oddeleni se stejnym nazvem jaky uz je v DB.");
                    return "redirect:./index";
                }


                if (title != null && title.isEmpty() == false && !" ".equals(title)){
                    d.setTitle(title.trim());
                }else {
                    d.setTitle(oldTitle);
                }

                System.out.println("new supervisor:" +  newSupervisor);

                if (newSupervisor != null && newSupervisor.isEmpty() == false && !" ".equals(newSupervisor) ){
                    System.out.println("new supervisor:" + "" + newSupervisor);
                    Employee e = employeeDB.findEmployeeByLogin(newSupervisor);
                    System.out.println("e login " + "" + e.getLogin_name());
                    d.setSupervisor(e.getLogin_name());
                }else {
                    System.out.println("Spatny novy supervisor - prida se stary.");
                    d.setSupervisor(oldSupervisor);
                }

                System.out.println("finalni supervisor" + d.getSupervisor());

                departmentRepository.alterDepartment(d.getId_department(), d.getTitle(), d.getSupervisor());

                messages.addFlashAttribute("successmessage", "ID oddeleni" + "" + d.getId_department() + "" + "uspesne upraveno.");

            }catch (Exception e){
                System.out.println("Nepodarilo se upravit oddeleni.");

                messages.addFlashAttribute("failuremessage", "Oddeleni se neulozilo." + e.getMessage());

            }






        }else if (access.equals("unknown")){
            return "redirect:./login";
        }else { return "redirect:./login";}




        return "redirect:./index";
    }


}
