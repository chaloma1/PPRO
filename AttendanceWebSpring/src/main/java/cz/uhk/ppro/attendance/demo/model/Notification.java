package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue
    private int id_notification;

    @ManyToOne
    @JoinColumn(name = "id_department", referencedColumnName = "id_department")
    private Department supervisor;

    @ManyToOne
    @JoinColumn(name = "id_deparmtnet",referencedColumnName = "id_department")
    private Department department;

    private String text;
    private String header;

    public Notification(Department supervisor, Department department, String text, String header) {
        this.supervisor = supervisor;
        this.department = department;
        this.text = text;
        this.header = header;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public Department getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Department supervisor) {
        this.supervisor = supervisor;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
