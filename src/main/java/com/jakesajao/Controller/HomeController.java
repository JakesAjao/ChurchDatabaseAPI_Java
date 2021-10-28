package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
import com.jakesajao.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    private AttendanceServiceImpl attendanceServiceImpl;

    @Autowired
    private UserService userService;
    public HomeController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }
    @ModelAttribute("absenteeFormDto")
    public AbsenteeFormDto absenteeFormDto() {
        return new AbsenteeFormDto();
    }
    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @GetMapping("/login?logout")
    public String logout(){
        System.out.println("Log out...1");
        return "login";
    }
    @GetMapping("/absentee")
    public String getAbsentee(Model model){
        return "absentee";
    }
    @PostMapping("/absentee")
    public String GetMemberByMonthYear(@ModelAttribute @Valid AbsenteeFormDto absenteeFormDto,
                                       BindingResult result, Model model){
        //attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        System.out.println("Absentee absenteeFormDto: " + absenteeFormDto);
        if (absenteeFormDto==null){
            System.out.println("absenteeFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("response", "Oops! No record.");
            return "/absentee";
        }
        //List<MemberAttend>memberList = attendanceRepository.FindMemberAttendanceByCategory("No");
        String category = null;
        LocalDate date = LocalDate.now();
        if (absenteeFormDto.getCategory().equals("2"))
            category = "No";
        else if (absenteeFormDto.getCategory().equals("1"))
            category = "Yes";
        else{
        System.out.println("absentee memberAttendList3: ");
        model.addAttribute("response1", "Category selected is invalid.");
        model.addAttribute("memberAttendList3", null);
        return "/absentee";
        }
        List<MemberAttend> memberPresentDateList = attendanceRepository.FindMemberAttendanceByCategoryAndDate(
                category,date.minusDays((Integer.parseInt(absenteeFormDto.getWeek())*7)));

        List memberAttendList3 = memberPresentDateList;
        System.out.println("absentee memberAttendList3: " + memberAttendList3);
        model.addAttribute("response", "Absentee records generated successfully!");
        model.addAttribute("memberAttendList3", memberAttendList3);

        return "/absentee";
    }


}
