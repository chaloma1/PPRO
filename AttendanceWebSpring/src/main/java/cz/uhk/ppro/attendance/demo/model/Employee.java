package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue
    private int id_employee;

    @Column(length = 50)
    private String first_name;
    @Column(length = 50)
    private String last_name;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id_department")
    private Department department;

    @Column(length = 50)
    private String tel_number;
    @Column(length = 50)
    private String email;
    @Column(length = 50)
    private String position;
    @Column(length = 50)
    private String heslo;

    @Column(length = 10)
    private int access_level;

    @OneToMany(mappedBy = "employee")
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "employee")
    private List<Request> requests;

    public Employee(String first_name, String last_name, String tel_number, String email, String position, String heslo, int access_level) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.tel_number = tel_number;
        this.email = email;
        this.position = position;
        this.heslo = heslo;
        this.access_level = access_level;
    }

    public int getAccess_level() {
        return access_level;
    }

    public void setAccess_level(int access_level) {
        this.access_level = access_level;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public int getId_employee() {
        return id_employee;
    }

    public void setId_employee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
