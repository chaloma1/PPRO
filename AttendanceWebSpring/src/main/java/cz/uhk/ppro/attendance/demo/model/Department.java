package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id_department;

    @Column
    private String title;

   /* @OneToOne
    private Employee supervisor;
    */
    @Column
    private String supervisor;

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "supervisor_notification")
    private List<Notification> supervisor_notifications;

    @OneToMany(mappedBy = "department")
    private List<Notification> notifications;

    public Department(){}

    public Department(String title) {
        this.title = title;
        supervisor = null;

    }

    public List<Notification> getSupervisor_notifications() {
        return supervisor_notifications;
    }

    public void setSupervisor_notifications(List<Notification> supervisor_notifications) {
        this.supervisor_notifications = supervisor_notifications;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public int getId_department() {
        return id_department;
    }

    public void setId_department(int id_department) {
        this.id_department = id_department;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }*/
}
