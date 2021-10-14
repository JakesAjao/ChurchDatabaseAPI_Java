package com.jakesajao;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.dto.MemberAttend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Configuration
public class BootsrapCommandRunner {
//    @Autowired
//    private AttendanceRepository attendanceRepository;
    @Bean
    CommandLineRunner commandLineRunner(MemberRepository memberRepository, AttendanceRepository attendanceRepository

    ) {
        return args -> {
            Attendance attendance = new Attendance();
            attendance.setStatus("No");
            //attendance.setId(3L);
            attendance.setCreatedDate(LocalDate.now());

            Optional<Member> memberData = memberRepository.findById(3L);

            System.out.println("Member Data: " + memberData.get());
            attendance.setMember(memberData.get());
            //attendanceRepository.save(attendance);

            List<Attendance> attendanceList = attendanceRepository.findAll();
            //System.out.println("Member Data List: " + attendanceList.toString());
            //List<Member> memberList = memberRepository.findAll();
            //for(Member member: memberList){
               //System.out.println("Joined Member: " + member);
                List<MemberAttend> memberList2 = attendanceRepository.findMemberAttend();
                System.out.println("Joined Member List: " + memberList2);
           // }
            //System.out.println("Joined Attendance Member List 2: " + attendanceList2);
        };
    }
}
