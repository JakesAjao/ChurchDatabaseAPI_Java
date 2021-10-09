package com.jakesajao.Repository;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    Member findByMobilephone1(String mobilephone);
}
