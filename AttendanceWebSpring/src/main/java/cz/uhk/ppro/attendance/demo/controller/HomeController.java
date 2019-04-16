package cz.uhk.ppro.attendance.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping("/")
    @ResponseBody
    public String action(){
        return "Ahoj!";
    }

    @RequestMapping("/index")
    public String helloworld() {
        return "home";
    }

    @RequestMapping("/test")
    public String testCSS(){ return "test"; }
}
