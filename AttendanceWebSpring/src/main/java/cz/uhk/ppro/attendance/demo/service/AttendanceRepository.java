package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional
@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {


    @Query(value = "SELECT * FROM attendance a WHERE a.id_employee = :id_employee AND a.attend_time > NOW() - INTERVAL 1 WEEK ORDER BY a.attend_time DESC", nativeQuery = true)
    List<Attendance> findRecentAttendances(@Param("id_employee") int id_employee);

    @Query(value = "SELECT * FROM attendance a WHERE a.id_employee = :id_employee AND a.attend_time > NOW() - INTERVAL 1 YEAR ORDER BY a.attend_time DESC", nativeQuery = true)
    List<Attendance> findYearAttendance(@Param("id_employee") int id_employee);

    @Modifying
    @Query(value = "UPDATE Attendance a SET a.attend_time = :attend_time, a.leave_time = :leave_time WHERE a.id_attendance = :id_attendance")
    void alterAttendance(@Param("id_attendance") int id_attendance, @Param("attend_time") Date attend_time, @Param("leave_time") Date leave_time);
}
