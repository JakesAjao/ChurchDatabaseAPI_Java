package com.jakesajao.Service;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.dto.MemberCreationDto;
import com.jakesajao.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private AttendanceRepository attendanceRepository;
    @Autowired
    private MemberService memberService;
    private final String status = "Active";
    private final String role = "ROLE_MEMBER";

    public Member findByMobilePhone1(String mobilephone1) {
        return memberRepository.findByMobilephone1(mobilephone1);
    }

    public Member save(MemberCreationDto registration) {
        Member member = new Member(registration.getFirstName(), registration.getLastName(),
                registration.getMobilephone1(), registration.getMobilephone2(), registration.getGender(),
                registration.getState(), registration.getTown(), registration.getAddress(), registration.getTitle(),
                status, role, LocalDate.now());

        System.out.println("Save member as : " + member);
        return memberRepository.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    public void UpdateMember(MemberCreationDto member1) {
        if (member1 == null) {
            System.out.println("Member is empty.");
            return;
        }
        if (memberRepository.findById(member1.getId()).isPresent()) {
            Member member = memberRepository.findById(member1.getId()).get();
            member.setFirstName(member1.getFirstName());
            member.setLastName(member1.getLastName());
            member.setTitle(member1.getTitle());
            member.setMobilephone1(member1.getMobilephone1());
            member.setMobilephone2(member1.getMobilephone2());
            member.setGender(member1.getGender());
            memberRepository.save(member);
            System.out.println("Member updated successfully.");
        }
    }
    @Transactional
    public Member DeleteMember(Long memberId) {
        if (memberId>0) {
            attendanceRepository.deleteAttendances(memberId);
            Member member = memberRepository.findById(memberId).get();
            memberRepository.deleteById(memberId);

            System.out.println("Member deleted successfully! Member:"+member);
            return member;
        }
        return null;
    }
    public long GenderCount(String gender){
        if (gender!=null){
            long genderVal = memberRepository.genderCount(gender);
            return genderVal;
        }
        return 0;
    }
    @Override
    public Page<Member> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.memberRepository.findAll(pageable);
    }
}


