package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Attendance;
import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.model.Notification;
import cz.uhk.ppro.attendance.demo.model.Request;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

public interface EmployeeDB {

    void createEmployee(Employee employee);

    void createAttendance(Attendance attendance);

    void makeRequest(List<Employee> requestedEmployees, Request request);

    Employee findEmployeeById(int employee_id);

    Employee findEmployeeByEmail(String email);

    Employee findEmployeeByLogin(String login);

    Boolean checkEmployeeLogin(String login);

    Employee findEmployeeByName(String firstName, String lastName);

    List<Employee> findAllEmployee();

    boolean removeEmployeeById(int employee_id);

    boolean removeEmployeeByEmail(String email);

    void createNotification(Notification notification);

    List<Attendance> viewMonthAttendance(int employee_id, Date month);

    Attendance viewDayAttendance(int employee_id, Date day);

    List<Request> viewUncompletedRequest(int employee_id);

    List<Request> viewCompletedRequest(int employee_id);

    List<Request> viewRequestCreatedByMe(String request_maker_name);

    boolean isDepartmentSupervisor(int employee_id);

    boolean isDepartmentSupervisor(String supervisor);

    List<Employee> findMembersFromDepartment(int employee_id, int department_id);

    String hashPassword(String heslo);

    boolean isValidUser(String login);

    String checkAccess(HttpSession session);
}
