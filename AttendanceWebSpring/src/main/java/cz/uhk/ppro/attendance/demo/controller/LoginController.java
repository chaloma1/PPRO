package cz.uhk.ppro.attendance.demo.controller;

import cz.uhk.ppro.attendance.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String home(){
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam String login_Name, @RequestParam String heslo) {

        System.out.println("Login" + login_Name);
        System.out.println(("heslo" + heslo));
        if(loginService.isLoggedIn(login_Name, heslo)) {
            System.out.println("JSEM LOGLEJ");
            session.setAttribute("login", login_Name);
            return "/index";
        }else {
            System.out.println("Nepovedlo se");
            return "/login";
        }


    }
}
