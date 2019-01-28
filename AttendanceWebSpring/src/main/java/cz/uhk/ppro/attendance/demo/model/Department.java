package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Department {
    @Id
    @GeneratedValue
    private int id_department;


    private String title;

    @OneToOne
    private Employee supervisor;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    @OneToMany(mappedBy = "supervisor")
    private List<Notification> supervisor_notifications;

    @OneToMany(mappedBy = "department")
    private List<Notification> notifications;

    public Department(String title, Employee supervisor) {
        this.title = title;
        this.supervisor = supervisor;
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

    public Employee getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Employee supervisor) {
        this.supervisor = supervisor;
    }
}
