package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    //@Query("Select a.status,a.createdDate,a.member from Member a left join Attendance b on a.id=b.id")
//    @Query("Select s from Member s where s.id=?1 ")
//    List<Member> findMemberById(Long id);
    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.firstName, b.lastName, a.status,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b")
    List<MemberAttend> findMemberAttend();
    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.firstName, b.lastName) "
            + " FROM Member b")
    List<MemberAttend> findMemberQuery();
    @Query(" SELECT distinct s.createdDate FROM Attendance s WHERE s.createdDate =:createdDate")
    Optional<LocalDate> ValidateCurrentExistingDate(LocalDate createdDate);

    @Modifying
    @Transactional
    @Query("update Attendance u set u.status = ?1 where u.createdDate= ?2 and u.members.id = ?3")
    int updateAttendanceById(String status,LocalDate createdDate,Long id);


//
//    @Query("Select u from Attendance u where u.createdDate= ?1 and u.id = ?2")
//   Attendance getAttendanceByIdAndDate(LocalDate createdDate,Long id);

}