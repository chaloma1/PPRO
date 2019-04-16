package cz.uhk.ppro.attendance.demo.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Request {
    @Id
    @GeneratedValue
    private int id_request;

    @ManyToOne
    @JoinColumn(name = "id_employee", referencedColumnName = "id_employee")
    private Employee requested_employee;

    private String request_maker_name;
    private String header;
    private String text;

    private Date date_of_creation;

    private Boolean completed;

    public Request(/*Employee employee,*/ String request_maker_name, String header, String text) {
        //this.requested_employee = employee;
        this.request_maker_name = request_maker_name;
        this.header = header;
        this.text = text;
        completed = false;
        date_of_creation = new Date(System.currentTimeMillis());

        /* add Date of creation and completed*/

    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Employee getRequested_employee() {
        return requested_employee;
    }

    public void setRequested_employee(Employee requested_employee) {
        this.requested_employee = requested_employee;
    }

    public String getRequest_maker_name() {
        return request_maker_name;
    }

    public void setRequest_maker_name(String request_maker_name) {
        this.request_maker_name = request_maker_name;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
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
