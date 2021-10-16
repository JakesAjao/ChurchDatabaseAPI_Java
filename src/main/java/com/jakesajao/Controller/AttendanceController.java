package com.jakesajao.Controller;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    AttendanceServiceImpl attendanceService;

    @ModelAttribute("attend")
    public MemberCreationDto memberCreationDto() {
        return new MemberCreationDto();
    }

    /*@GetMapping("/attendance")
    public String getAttendance(Model model){
        AttendanceCreationDto attendance = new AttendanceCreationDto();
        attendance.setStatus("Yes");
        Optional<Member> memberData = memberRepository.findById(3L);

        System.out.println("Member Data: "+memberData.get());
        attendance.setMember(memberData.get());
        attendanceService.Save(attendance);

        //List<Attendance> attendanceList = attendanceRepository.findAll();
        //List<Attendance> attendanceList2 = attendanceRepository.findAttendanceById();
        //System.out.println("Joined Attendance Member List: "+attendanceList2);

        //model.addAttribute("attendanceList",attendanceList2);
        return "/attendance";
    }*/
    @PostMapping("/attendance")
    public String addNewMember(@ModelAttribute("member") @Valid AttendanceCreationDto attendDto,
                               BindingResult result) {
    //public String postAttendance(@ModelAttribute("attendance")Attendance attendance){
        System.out.println("Attendance has been posted");
        System.out.println(attendDto);
        attendanceService.Save(attendDto);
        return "/attendance";
    }
    //Inside data into Attendance table
}
