package com.jakesajao.Service;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberAttend;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService{
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Override
    public Attendance Save(AttendanceCreationDto attendanceCreationDto) {
        Attendance attendance = new Attendance(attendanceCreationDto.getMember(), attendanceCreationDto.getStatus()
               ,LocalDate.now());

        System.out.println("Save attendance as : " + attendance);
        return attendanceRepository.save(attendance);
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
    public int UpdateMemberAttendance_NewWeek(String status,LocalDate eventDate,Long id) {
        LocalDate todayDate = LocalDate.now();
        int response = 0;
        int matchDate = eventDate.compareTo(todayDate);
        if (matchDate == 0) {
            System.out.println("Date matches!: matchDate: "+matchDate);
            response = attendanceRepository.updateAttendanceById(status, eventDate, id);
            System.out.println("Update Attendance response succeeded! response: "+response);
            return response;//1
        } else {
            response = -1;
            System.out.println("Date matches oops: matchDate: "+matchDate);
            System.out.println("Update Attendance response failed. ");

            return response;
      }
    }


}
