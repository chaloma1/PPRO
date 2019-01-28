package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id_request;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee employee;
    private String header;
    private String text;

    public Request(Employee employee, String header, String text) {
        this.employee = employee;
        this.header = header;
        this.text = text;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
