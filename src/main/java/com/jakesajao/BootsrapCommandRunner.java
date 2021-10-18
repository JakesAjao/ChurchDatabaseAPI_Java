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
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

//@Configuration
public class BootsrapCommandRunner {
   @Autowired
 private AttendanceRepository attendanceRepository;
   @Autowired
   private MemberRepository memberRepository;
    //@Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Attendance attendance = new Attendance();
            attendance.setStatus("No");
            //attendance.setId(3L);
            attendance.setCreatedDate(LocalDate.now());

            List<MemberAttend> attendList2 = attendanceRepository.findMemberAttend();//To be displayed as a list to users
            System.out.println("Attendance List: " + attendList2);

            SaveMemberAttendance_NewWeek();//To be run once everytime you loop attendance page

            LocalDate eventDate = LocalDate.now();
            String status = "Yes";
            Long id = 3L;
            UpdateMemberAttendance_NewWeek(status,eventDate,id); //To be run after attendance status update


               // List<Attendance> attendanceList = attendanceRepository.findAll();
            //System.out.println("Member Data List: " + attendanceList.toString());
            //List<Member> memberList = memberRepository.findAll();
            //for(Member member: memberList){
               //System.out.println("Joined Member: " + member);
            //List<MemberAttend> memberList2 = memberRepository.findMemberAttend();
            //System.out.println("Joined Member List: " + memberList2);

            List<MemberAttend> MemberList = attendanceRepository.findMemberQuery();//fetch id alone fine
            System.out.println("Member Only List: " + MemberList);

        };
    }
    public void SaveMemberAttendance_NewWeek(){
        LocalDate currentDate = LocalDate.now();
        Optional<LocalDate> dateAlreadyExist = attendanceRepository.ValidateCurrentExistingDate(currentDate);
        if (dateAlreadyExist.isEmpty()) {
            List<MemberAttend> memberAttendList = attendanceRepository.findMemberQuery();
            memberAttendList.forEach(memberAttend -> {
                Optional<Member> memberObj = memberRepository.findById(memberAttend.getId());
                Attendance attend = new Attendance();
                attend.setMember(memberObj.get());
                attend.setStatus("No");
                attend.setCreatedDate(currentDate);
            attendanceRepository.save(attend);
            System.out.println("Attendance saved successfully! ");
            });

        }
    }

    public void UpdateMemberAttendance_NewWeek(String status,LocalDate eventDate,Long id){
        int response = attendanceRepository.updateAttendanceById(status,eventDate,id);
        if (response==1)
        System.out.println("Update Attendance response succeeded! ");
        else
            System.out.println("Update Attendance response failed. ");
    }
}
