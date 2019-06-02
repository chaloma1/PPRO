package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Department;
import cz.uhk.ppro.attendance.demo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class InitDatabaseService {

    @PersistenceContext
    EntityManager em;

    private EmployeeDB employeeDB;

    private DepartmentRepository departmentRepository;

    private AttendanceRepository attendanceRepository;

    public InitDatabaseService(@Autowired EmployeeDB employeeDB,
                               @Autowired DepartmentRepository departmentRepository,
                               @Autowired AttendanceRepository attendanceRepository){
        this.employeeDB = employeeDB;
        this.departmentRepository = departmentRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public void initDB(){

        attendanceRepository.deleteAll();

        em.createQuery("DELETE FROM Employee").executeUpdate();
        departmentRepository.deleteAll();

        Department department = new Department("Management");
        departmentRepository.save(department);

        Employee employee = new Employee("Martin","Chaloupka", "",
                "","test admin","admin", "admin", 0 );
        employee.setDepartment(department);
        employeeDB.createEmployee(employee);
    }

    public void initEmployee() {
        Employee employee = employeeDB.findEmployeeByName("Martin", "Chaloupka");
        if(employee != null)
        {

        }
    }
}
