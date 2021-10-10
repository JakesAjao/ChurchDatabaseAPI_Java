package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMobilephone1(String mobilephone);
}
