package com.jakesajao.Repository;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import com.jakesajao.dto.MemberAttend;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> { //extends JpaRepository<Member,Long> {
    Member findByMobilephone1(String mobilephone);

}
