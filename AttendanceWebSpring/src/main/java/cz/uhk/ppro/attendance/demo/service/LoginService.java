package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    EmployeeDB employeeDB;


    public String findLoggedInUserName(){

        return null;
    }

    public boolean isLoggedIn(String login, String heslo){
        Employee employee;
        try {
            System.out.println("try");
            employee = employeeDB.findEmployeeByLogin(login);
            System.out.println(employee.getLoginName());
            if (employee.getHeslo().equals(heslo)){
                System.out.println("true");
                return true;
            }else {
                System.out.println("false");
                return false;
            }
        }catch (Exception e)
        {
            System.out.println("catch");
            System.out.println(e.getMessage());
            return false;
        }



    }
}
