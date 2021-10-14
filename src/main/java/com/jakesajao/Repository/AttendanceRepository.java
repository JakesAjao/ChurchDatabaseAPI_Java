package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    //@Query("Select a.status,a.createdDate,a.member from Member a left join Attendance b on a.id=b.id")
//    @Query("Select s from Member s where s.id=?1 ")
//    List<Member> findMemberById(Long id);
    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.firstName, b.lastName, a.status,b.gender,a.createdDate) "
            + " FROM Attendance a left JOIN a.members b where a.id=b.id")
            //+" FROM Attendance a left JOIN member b on a.id=b.id")
   //@uery("SELECT b.First_Name, b.last_Name,a.status,b.gender,a.created_date FROM Attendance a left JOIN member b on a.id=b.id");
    List<MemberAttend> findMemberAttend();
}