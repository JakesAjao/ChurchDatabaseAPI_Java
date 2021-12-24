package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.title, b.firstName, b.lastName,a.present,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b where a.createdDate=?1 order by a.createdDate desc")
    List<MemberAttend> findMemberAttendCurrentDate(LocalDate currentDate);

    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.title, b.firstName, b.lastName,a.present,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b order by a.createdDate desc")
    List<MemberAttend> findMemberAttend();


    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.firstName, b.lastName) "
            + " FROM Member b")
    List<MemberAttend> findMemberQuery();
    @Query(" SELECT distinct s.createdDate FROM Attendance s WHERE s.createdDate =:createdDate")
    Optional<LocalDate> ValidateCurrentExistingDate(LocalDate createdDate);

    @Modifying
    @Transactional
    @Query("update Attendance u set u.present = ?1,u.createdDate= ?2 where u.createdDate= ?4 and u.members.id = ?3")
    int updateAttendanceById(String present,LocalDate createdDate,Long id,LocalDate todayDate);

    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.title, b.firstName, b.lastName,a.present,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b WHERE extract(month from a.createdDate)=?1 and " +
            "extract(year from a.createdDate)=?2")
    List<MemberAttend>FindMemberAttendanceByMonthYear(int month,int year);

    @Query("SELECT new com.jakesajao.dto.MemberAttend(a.id,b.title, b.firstName, b.lastName,a.present,b.gender,a.createdDate) "
            + " FROM Attendance a inner JOIN a.members b where a.present=?1 ")
    List<MemberAttend>FindMemberAttendanceByCategory(String present);
    @Query("SELECT new com.jakesajao.dto.MemberAttend(b.id,b.title, b.firstName, b.lastName,a.present,b.gender,a.createdDate,b.mobilephone1) "
            + " FROM Attendance a inner JOIN a.members b where a.present=?1 " +
            " and  (a.createdDate >=?2)")
    List<MemberAttend>FindMemberAttendanceByCategoryAndDate(String present,LocalDate date);

    @Modifying
    @Query("delete from Attendance b where b.members.id=:memberId")
    void deleteAttendances(Long memberId);


}