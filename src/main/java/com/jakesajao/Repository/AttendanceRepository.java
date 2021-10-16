package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    //@Query("Select a.status,a.createdDate,a.member from Member a left join Attendance b on a.id=b.id")
//    @Query("Select s from Member s where s.id=?1 ")
//    List<Member> findMemberById(Long id);
    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.firstName, b.lastName, a.status,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b")
    List<MemberAttend> findMemberAttend();
    @Query(" SELECT s FROM Attendance s WHERE s.createdDate =:createdDate and s.id=:member_id")
    Attendance DateIdAlreadyExist(LocalDate createdDate, Long member_id);

}