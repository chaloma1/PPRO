package cz.uhk.ppro.attendance.demo.controller;

import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.service.EmployeeDB;
import cz.uhk.ppro.attendance.demo.service.EmployeeDao;
import cz.uhk.ppro.attendance.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private EmployeeDB empDB;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(){
        return "./login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam String login_Name, @RequestParam String heslo, RedirectAttributes redirectAttributes) {

        System.out.println("Login" + login_Name);
        System.out.println(("heslo" + heslo));
        if(loginService.isLoggedIn(login_Name, heslo)) {
            System.out.println("JSEM LOGLEJ");

            Employee e = empDB.findEmployeeByLogin(login_Name);
            session.setAttribute("login", login_Name);
            session.setAttribute("access", e.getAccess_level());
            redirectAttributes.addFlashAttribute("messagenew", "SUPR LOGIN");

            return "redirect:./index";
        }else {
            System.out.println("Nepovedlo se");
            return "redirect:./login";
        }


    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        if(session.getAttribute("login") != null)
        {
            session.removeAttribute("login");
        }


        if(session.getAttribute("access") != null)
        {
            session.removeAttribute("access");
        }
        return "./login";
    }
}
