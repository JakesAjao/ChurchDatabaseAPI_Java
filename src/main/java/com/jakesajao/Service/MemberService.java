package com.jakesajao.Service;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import com.jakesajao.dto.MemberCreationDto;
import com.jakesajao.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Member findByMobilePhone1(String mobilephone1);
    Member save(MemberCreationDto creation);
    //UserDetails loadUserByUsername(String email);
    void UpdateMember(Member member);
}
