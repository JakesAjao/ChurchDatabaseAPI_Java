package com.jakesajao.Controller;

import com.jakesajao.Model.*;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.AttendanceControllerUtility;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.Service.LoginControllerUtility;
import com.jakesajao.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AttendanceServiceImpl attendanceService;
    private int PERCENTAGE = 0;
    private AttendanceServiceImpl attendanceServiceImpl;
    private final int mark = 25;

    @ModelAttribute("chartFormDto")
    public ChartFormDto chartFormDto() {
        return new ChartFormDto();
    }
    public AttendanceController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }
    @PostMapping("/search")
    public String GetMemberByMonthYear(@ModelAttribute @Valid ChartFormDto chartFormDto,
                                       BindingResult result,Model model){
        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        AttendanceControllerUtility.ValidateGetMemberByMonthYearLogic(chartFormDto, model);

        List<MemberAttend> getListCriteriaFromDB = attendanceRepository.FindMemberAttendanceByMonthYear(Integer.parseInt(chartFormDto.getMonth()),
                Integer.parseInt(chartFormDto.getYear()));

        List memberAttendList3 = attendanceServiceImpl.ProcessChart(mark,getListCriteriaFromDB);
        System.out.println("searchchart memberAttendList3: " + memberAttendList3);
        model.addAttribute("success", "Attendance records generated successfully!");
        model.addAttribute("memberAttendList3", memberAttendList3);

        return "/charts";
    }
    @GetMapping("/attendance")
    public String updateAttendance(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();

            model.addAttribute("firstName", currentUserName);
        }
        else{
            return "/login";
        }
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttendCurrentDate(currentDate);//To be displayed as a list to users
        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        memberAttendList2 = AttendanceControllerUtility.UpdateAttendanceLogic(memberAttendList);

        MemberAttendDto attendDto = new MemberAttendDto();
        memberAttendList2.forEach(memberAttend -> {
            attendDto.addMemberAttend(memberAttend);
        });
        System.out.println("attendDto List: " + attendDto);
        model.addAttribute("form", attendDto);
        return "attendance";
    }

    @PostMapping("/attendance")
    public String postAttendance(@ModelAttribute MemberAttendDto form,Model model,@RequestParam(name="eventDate", required = true) String eventDate,
                                 RedirectAttributes redirectAttributes){
        System.out.println("form: "+form);
        System.out.println("eventDate: "+eventDate);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate eventDate2 = LocalDate.parse(eventDate, formatter);
        //https://www.java67.com/2016/04/how-to-convert-string-to-localdatetime-in-java8-example.html#ixzz7DWFu0rmR
        List<MemberAttend> attendList = form.getAttends();
        final int[] response = new int[1];
        AttendanceControllerUtility.PostAttendanceLogic(eventDate2,attendList, response, attendanceServiceImpl, redirectAttributes);

        return "redirect:/attendance";

    }
    @GetMapping("/charts")
    public String getCharts(Model model){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();

                model.addAttribute("firstName", currentUserName);
            } else {
                return "/login";
            }
           /* attendanceServiceImpl.SaveMemberAttendance_NewWeek();
            List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();
            List memberAttendList3 = attendanceServiceImpl.ProcessChart(mark, memberAttendList);

            System.out.println("memberAttendList3 List: " + memberAttendList3);

            model.addAttribute("memberAttendList3", memberAttendList3);*/

            return "/charts";
        }
        catch(Exception e){
            System.out.println("Exception "+e);
            return "/login";
        }

    }


}
