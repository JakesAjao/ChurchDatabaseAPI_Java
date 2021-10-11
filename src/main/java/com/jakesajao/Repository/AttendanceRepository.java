package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
//    @Query("Select a.id,a.createdDate,a.status,b.firstName,b.lastName," +
//            "b.title,b.gender from Attendance a join Member b on a.id=b.id")
   // public List<Attendance> FindAllWithDescriptionQuery();
}
