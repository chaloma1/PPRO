package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Attendance {
    @Id
    @GeneratedValue
    private int id_attendance;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee employee;
    private Date attend_time;
    private Date leave_time;

    public Attendance(Employee employee, Date attend_time, Date leave_time) {
        this.employee = employee;
        this.attend_time = attend_time;
        this.leave_time = leave_time;
    }

    public int getId_attendance() {
        return id_attendance;
    }

    public void setId_attendance(int id_attendance) {
        this.id_attendance = id_attendance;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getAttend_time() {
        return attend_time;
    }

    public void setAttend_time(Date attend_time) {
        this.attend_time = attend_time;
    }

    public Date getLeave_time() {
        return leave_time;
    }

    public void setLeave_time(Date leave_time) {
        this.leave_time = leave_time;
    }
}
