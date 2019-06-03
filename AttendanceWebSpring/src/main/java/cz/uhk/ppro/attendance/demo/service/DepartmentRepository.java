package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {


    Department findByTitle(String title);



    @Modifying
    @Query(value = "UPDATE Department d SET d.title = :newtitle, d.supervisor = :newsupervisor WHERE d.id_department = :id_department")
    void alterDepartment(@Param("id_department") int id_department, @Param("newtitle") String newtitle, @Param("newsupervisor") String newsupervisor);

}
