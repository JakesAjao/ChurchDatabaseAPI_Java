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
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Optional;

//@Configuration
public class BootsrapCommandRunner {
   @Autowired
 private AttendanceRepository attendanceRepository;
   @Autowired
   private MemberRepository memberRepository;
   // @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Attendance attendance = new Attendance();
            attendance.setPresent("No");
            //attendance.setId(3L);
            attendance.setCreatedDate(LocalDate.now());
            List<MemberAttend>memberList = attendanceRepository.FindMemberAttendanceByCategory("No");

            LocalDate date = LocalDate.now().minusDays(40);
            List<MemberAttend> memberPresentDateList = attendanceRepository.FindMemberAttendanceByCategoryAndDate(
                    "No",date);
            System.out.println("memberPresentDateList: "+memberPresentDateList);

        };
    }
    private int WeekOftheYear(int year, int month, int day){
        LocalDate date = LocalDate.of(year, month, day);
        int weekOfYear = date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
        return weekOfYear;
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
                attend.setPresent("No");
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
