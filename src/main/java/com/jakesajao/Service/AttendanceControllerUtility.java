package com.jakesajao.Service;

import com.jakesajao.dto.ChartFormDto;
import com.jakesajao.dto.MemberAttend;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceControllerUtility {
    public static void PostAttendanceLogic(LocalDate eventDate2,List<MemberAttend> attendList, int[] response, AttendanceServiceImpl attendanceServiceImpl,
                                           RedirectAttributes redirectAttributes) {
        attendList.forEach(attend -> {
            if (attend.getPresent1() == true) {
                attend.setPresent("Yes");
                System.out.println("Attend: " + attend);
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getPresent(), eventDate2, attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
                redirectAttributes.addFlashAttribute("success2", "Attendance updated successfully!");
            } else {
                attend.setPresent("No");
                //response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getPresent(), attend.getCreatedDate(), attend.getId());
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getPresent(), eventDate2, attend.getId());

                System.out.println("Posted successfully id: " + attend.getId());
                redirectAttributes.addFlashAttribute("success2", "Attendance updated successfully!");
            }
        });
    }
    public static List<MemberAttend> UpdateAttendanceLogic(List<MemberAttend> memberAttendList){
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getPresent().equals("Yes")) {
                memberAttend.setPresent1(true);
            }
            else{
                memberAttend.setPresent1(false);
            }
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
                    memberAttend.getLastName(),memberAttend.getPresent1(),memberAttend.getWeek1(),memberAttend.getWeek2(),
                    memberAttend.getWeek3(),memberAttend.getWeek4(), memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
            memberAttendList2.add(memberAttend1);

        });
        return memberAttendList2;
    }
    public static String ValidateGetMemberByMonthYearLogic(@ModelAttribute @Valid ChartFormDto chartFormDto, Model model) {
        if (chartFormDto == null) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("response", "Oops! No record.");
            return "/charts";
        }
        if (chartFormDto == null) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("success", "Oops! No record.");
            return "/charts";
        }
        if (chartFormDto.getMonth().equals("Select the Month")) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("error", "Selected Month is invalid.");
            return "/charts";
        } else if (chartFormDto.getYear().equals("Select the Year")) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("error1", "Selected Year is invalid.");
            return "/charts";
        }
       return null;
    }
    public static MemberAttend WeekOfMonthSwitch(int weekOfMonth,MemberAttend memberAttend, int mark){
        int PERCENTAGE = 0;
        switch(weekOfMonth) {
            case 1:
                if (memberAttend.getPresent1()) {
                    memberAttend.setWeek1(true);
                    PERCENTAGE = PERCENTAGE + 25;
                    memberAttend.setPercentage(PERCENTAGE);
                    break;
                }
            case 2:
                if (memberAttend.getPresent1()) {
                    memberAttend.setWeek2(true);
                    PERCENTAGE = PERCENTAGE + 25;
                    memberAttend.setPercentage(PERCENTAGE);
                    break;
                }
                case 3:
                if (memberAttend.getPresent1()) {
                    memberAttend.setWeek3(true);
                    PERCENTAGE = PERCENTAGE + 25;
                    memberAttend.setPercentage(PERCENTAGE);
                    break;
                }
            case 4:
                if (memberAttend.getPresent1()) {
                    memberAttend.setWeek4(true);
                    PERCENTAGE = PERCENTAGE + 25;
                    memberAttend.setPercentage(PERCENTAGE);
                    break;
                }
        }
        memberAttend.setPercentage(PERCENTAGE);
        return memberAttend;
    }
}
