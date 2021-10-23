package com.jakesajao.Controller;

import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.dto.AttendanceCreationDto;
import com.jakesajao.dto.MemberAttend;
import com.jakesajao.dto.MemberAttendDto;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AttendanceServiceImpl attendanceService;
    private int PERCENTAGE = 0;
    private AttendanceServiceImpl attendanceServiceImpl;
    private final int mark = 25;

    public AttendanceController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }

    @GetMapping("/attendance")
    public String updateMember(Model model) {
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();//To be displayed as a list to users

        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getStatus().equals("Yes")) {
                memberAttend.setPresent(true);
            }
            else{
                memberAttend.setPresent(false);
            }
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
                    memberAttend.getLastName(),memberAttend.getPresent(),memberAttend.isWeek1(),memberAttend.isWeek2(),
                    memberAttend.isWeek3(),memberAttend.isWeek4(), memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
            memberAttendList2.add(memberAttend1);

        });

        MemberAttendDto attendDto = new MemberAttendDto();
        memberAttendList2.forEach(memberAttend -> {
            attendDto.addMemberAttend(memberAttend);
        });

        System.out.println("attendDto List: " + attendDto);
        model.addAttribute("form", attendDto);

        return "attendance";
    }
    @PostMapping("/attendance")
    public String postAttendance(@ModelAttribute MemberAttendDto form,Model model){
        System.out.println("form: "+form);
        List<MemberAttend> attendList = form.getAttends();
        final int[] response = new int[1];
        attendList.forEach(attend->{
            if (attend.getPresent()==true) {
                attend.setStatus("Yes");
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getStatus(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
            else {
                attend.setStatus("No");
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getStatus(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
        });

        return "redirect:/attendance?success";

    }
    @GetMapping("/charts")
    public String getCharts(Model model){
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();//To be displayed as a list to users

        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        Map<String,MemberAttend> memberAttendMap = new HashMap<>();
        PERCENTAGE = 0;
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getStatus().equals("Yes")) {
                memberAttend.setPresent(true);
            }
            else{
                memberAttend.setPresent(false);
            }
            int weekOfMonth = processWeekOfMonth(memberAttend.getCreatedDate());
            if (weekOfMonth==1){
                memberAttend.setWeek1(true);
                PERCENTAGE = PERCENTAGE + mark;
            }
            else if (weekOfMonth==2) {
                memberAttend.setWeek2(true);
                PERCENTAGE = PERCENTAGE + mark;
            }
            else if (weekOfMonth==3) {
                memberAttend.setWeek3(true);
                PERCENTAGE = PERCENTAGE + mark;
            }
            else if (weekOfMonth==4) {
                memberAttend.setWeek4(true);
                PERCENTAGE = PERCENTAGE + mark;
            }
            memberAttend.setPercentage(PERCENTAGE);
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
                    memberAttend.getLastName(), memberAttend.getPresent(),memberAttend.isWeek1(),memberAttend.isWeek2(),
                    memberAttend.isWeek3(),memberAttend.isWeek4(),memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
            MemberAttend memberAttend2 = memberAttendMap.get(memberAttend.getFirstName());
            if (memberAttend2!=null){
            //memberAttend already exists.
                if (memberAttend1.isWeek1()==true) {
                    memberAttend2.setWeek1(true);
                    PERCENTAGE = PERCENTAGE + mark;
                }
                if (memberAttend1.isWeek2()==true) {
                    PERCENTAGE = PERCENTAGE + mark;
                    memberAttend2.setWeek2(true);
                }
                if(memberAttend1.isWeek3()==true) {
                    PERCENTAGE = PERCENTAGE + mark;
                    memberAttend2.setWeek3(true);
                }
                if (memberAttend1.isWeek4()==true) {
                    memberAttend2.setWeek4(true);
                    PERCENTAGE = PERCENTAGE + mark;
                }
                memberAttend2.setPercentage(PERCENTAGE);
                    MemberAttend memberAttendUpdate = new MemberAttend(memberAttend2.getId(), memberAttend2.getTitle(), memberAttend2.getFirstName(),
                            memberAttend2.getLastName(), memberAttend2.getPresent(), memberAttend2.isWeek1(), memberAttend2.isWeek2(),
                            memberAttend2.isWeek3(), memberAttend2.isWeek4(), memberAttend2.getPercentage(), memberAttend2.getGender(), memberAttend2.getCreatedDate());
                System.out.println("memberAttendUpdate: "+memberAttendUpdate);
                    memberAttendMap.computeIfPresent(memberAttend.getFirstName(), (key, val) -> memberAttendUpdate);
                }
            else{
                memberAttendMap.put(memberAttend.getFirstName(),memberAttend1);
            }

           // memberAttendMap.put(memberAttend.getFirstName(),memberAttend1);
//            memberAttend.setPercentage(PERCENTAGE);
//            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
//                    memberAttend.getLastName(), memberAttend.getPresent(),memberAttend.isWeek1(),memberAttend.isWeek2(),
//                    memberAttend.isWeek3(),memberAttend.isWeek4(),memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
           // memberAttendList2.add(memberAttend1);
            PERCENTAGE=0;
        });
        List memberAttendList3 = memberAttendMap.values()
                .stream()
                .collect(Collectors.toList());

        System.out.println("memberAttendList3 List: " + memberAttendList3);
        model.addAttribute("memberAttendList3", memberAttendList3);

        return "/charts";
    }
    private int processWeekOfMonth(LocalDate dates){
        LocalDate date = dates;
        System.out.println("Week of the month: "+date.get(WeekFields.ISO.weekOfMonth()));
        return date.get(WeekFields.ISO.weekOfMonth());
    }

    //Inside data into Attendance table
}
