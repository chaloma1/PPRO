package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Attendance;
import cz.uhk.ppro.attendance.demo.model.Employee;
import cz.uhk.ppro.attendance.demo.model.Notification;
import cz.uhk.ppro.attendance.demo.model.Request;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class EmployeeDao implements EmployeeDB {

    @PersistenceContext
    private EntityManager em;
    // TODO component




    @Override
    public void createEmployee(Employee employee) {
        em.persist(employee);

    }

    @Override
    public void createAttendance(Attendance attendance) {
        em.persist(attendance);

    }

    @Override
    public void makeRequest(List<Employee> requestedEmployees, Request request) {
        for(int i = 0; i < requestedEmployees.size(); i++){
            request.setRequested_employee(requestedEmployees.get(i));
            em.persist(request);
        }

    }

    @Override
    public Employee findEmployeeById(int employee_id) {
        return (Employee) em.createQuery(
                "Select e from Employee e where e.id_employee = :employee_id").setParameter("employee_id", employee_id).getSingleResult();
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return (Employee) em.createQuery(
                "Select e from Employee e where e.email LIKE CONCAT('%',:email,'%')").setParameter("email", email).getSingleResult();
    }

    @Override
    public Employee findEmployeeByLogin(String login) {
        return (Employee) em.createQuery(
                "Select e from Employee e where e.login_name LIKE CONCAT('%',:login,'%')").setParameter("login", login).getSingleResult();
    }

    @Override
    public Employee findEmployeeByName(String firstName, String lastName) {
        return (Employee) em.createQuery(
                "Select e from Employee e where e.first_name LIKE CONCAT('%',:firstName,'%') and e.last_name LIKE CONCAT('%',:lastName,'%')")
                .setParameter("firstName", firstName).setParameter("lastName", lastName).getSingleResult();
    }

    @Override
    public List<Employee> findAllEmployee() {
        List<Employee> employees = em.createQuery("Select e from Employee e").getResultList();
        return employees;
    }

    @Override
    public boolean removeEmployeeById(int employee_id) {
        return false;
    }

    @Override
    public boolean removeEmployeeByEmail(String email) {
        return false;
    }

    @Override
    public void createNotification(Notification notification) {

    }

    @Override
    public List<Attendance> viewMonthAttendance(int employee_id, Date month) {
        return em.createQuery(
                "Select a from Attendance a where id_employee = :employee_id and MONTH(a.attend_time) = MONTH(:month)")
                .setParameter("employee_id", employee_id).setParameter("month", month).getResultList();
    }

    @Override
    public Attendance viewDayAttendance(int employee_id, Date day) {
        return (Attendance) em.createQuery(
                "Select a from Attendance a where id_employee = :employee_id and DAY(a.attend_time) = DAY(:day)")
                .setParameter("employee_id", employee_id).setParameter("day", day).getSingleResult();
    }

    @Override
    public List<Request> viewUncompletedRequest(int employee_id) {
        return em.createQuery(
                "Select r from Request r where r.requested_employee = :employee_id and r.completed = 'false'")
                .setParameter("employee_id", employee_id).getResultList();
    }

    @Override
    public List<Request> viewCompletedRequest(int employee_id) {
        return em.createQuery(
                "Select r from Request r where r.requested_employee = :employee_id and r.completed = 'true'")
                .setParameter("employee_id", employee_id).getResultList();
    }


    @Override
    public List<Request> viewRequestCreatedByMe(String request_maker_name) {
        return em.createQuery(
                "Select r from Request r where r.request_maker_name LIKE CONCAT('%',:request_maker_name,'%')")
                .setParameter("request_maker_name", request_maker_name).getResultList();
    }

    @Override
    public boolean isDepartmentSupervisor(int employee_id) {
        Employee e = findEmployeeById(employee_id);
        int supervisor = em.createQuery("SELECT COUNT(d) FROM Department d WHERE d.supervisor LIKE CONCAT('%',:login,'%') ").setParameter("login",e.getLogin_name()).getFirstResult();

        if(supervisor == 0) {
            return false;
        }
        return true;
    }

    @Override
    public String checkAccess(HttpSession session) {
            if(session.getAttribute("access") != null){
                String access_level;
                switch ((int)session.getAttribute("access"))
                {
                    case 0: access_level = "admin";break;
                    case 1: access_level = "employee";break;
                    default: access_level = "unknown";
                }

                return access_level;


            }else
            {
                return "unknown";
            }

    }
}
