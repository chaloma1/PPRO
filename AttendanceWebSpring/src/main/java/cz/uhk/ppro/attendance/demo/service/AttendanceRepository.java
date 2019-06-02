package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

    @Query(value = "SELECT * FROM attendance a WHERE a.id_employee = :id_employee AND a.attend_time > NOW() - INTERVAL 1 WEEK ORDER BY a.attend_time DESC", nativeQuery = true)
    List<Attendance> findRecentAttendances(@Param("id_employee") int id_employee);
}
