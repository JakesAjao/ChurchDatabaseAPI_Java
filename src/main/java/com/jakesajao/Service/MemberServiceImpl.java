package com.jakesajao.Service;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.dto.MemberCreationDto;
import com.jakesajao.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    public Member findByMobilePhone1(String mobilephone1) {
        return memberRepository.findByMobilephone1(mobilephone1);
    }

    public Member save(MemberCreationDto registration) {
        Member member = new Member(registration.getFirstName(), registration.getLastName(),
                registration.getMobilephone1(), registration.getMobilephone2(), registration.getGender(),
                registration.getState(),registration.getTown(),registration.getAddress(),registration.getTitle(),
                "ROLE_MEMBER",LocalDate.now());

        System.out.println("Save member as : " + member);
        return memberRepository.save(member);
    }

    @Override
    public void UpdateMember(Member member) {

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
//    public void UpdateUser(User_ user){
//        if (user==null){
//            System.out.println("User is empty.");
//            return;
//        }
//        user.setFirstName(user.getFirstName());
//        user.setLastName(user.getLastName());
//        user.setEmail(user.getEmail());
//        List<Role> role = new ArrayList<>();
//        userRepository.save(user);
//        System.out.println("User Saved successfully. User: "+user);
//    }
//    }


