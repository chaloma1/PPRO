package cz.uhk.ppro.attendance.demo.service;

import cz.uhk.ppro.attendance.demo.model.Request;
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
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query(value = "SELECT * from request r WHERE r.id_employee = :id_employee AND r.date_of_completion IS NULL AND r.date_of_creation > NOW() - INTERVAL 1 WEEK ORDER BY r.date_of_creation DESC", nativeQuery = true)
    List<Request> findUserWeekRequests(@Param("id_employee") int id_employee);

    @Query(value = "SELECT * from request r WHERE r.request_maker_name LIKE :login AND r.date_of_creation > NOW() - INTERVAL 1 WEEK ORDER BY r.date_of_creation DESC", nativeQuery = true)
    List<Request> findMyRequests(@Param("login") String login);


    @Modifying
    @Query(value = "UPDATE Request r SET r.date_of_completion = :completion_date WHERE r.id_request = :id_request")
    void alterRequestCompleted(@Param("id_request") int id_request, @Param("completion_date") Date completion_date);
}
