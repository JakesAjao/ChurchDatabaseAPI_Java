package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMobilephone1(String mobilephone);
//    @Query("SELECT new com.jakesajao.dto.MemberAttend(a.firstName, a.lastName, b.status,a.gender,b.createdDate) "
//            + " FROM Member a left JOIN a.attendances b")
//    List<MemberAttend> findMemberAttend();
}
