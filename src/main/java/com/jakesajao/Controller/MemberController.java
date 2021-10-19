package com.jakesajao.Controller;


import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
import com.jakesajao.dto.MemberAttend;
import com.jakesajao.dto.MemberCreationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MemberService memberService;
    static Set<Attendance> setAttend = new HashSet<>();

    @ModelAttribute("memberAttend")
    public MemberAttend memberAttend() {
        return new MemberAttend();
    }

    @GetMapping("/add")
    public String addMember(Model model) {
        return "add";
    }

    @GetMapping("/attendance")
    public String updateMember(Model model) {
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();//To be displayed as a list to users
        System.out.println("Attendance List: " + memberAttendList);
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getStatus().equals("Yes")) {
                memberAttend.setPresent(true);
            }
            else{
                memberAttend.setPresent(false);
            }
                MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(), memberAttend.getFirstName(),
                        memberAttend.getLastName(), memberAttend.getPresent(), memberAttend.getGender(), memberAttend.getCreatedDate());
                memberAttendList2.add(memberAttend1);

        });
        System.out.println("Attendance memberAttendList2: " + memberAttendList2);

        //MemberAttend memberAttend = new MemberAttend();
        //model.addAttribute("memberAttend", memberAttend);
        model.addAttribute("memberAttendList", memberAttendList2);
        LocalDate todayDate = LocalDate.now();
        System.out.println("Member List: " + memberAttendList);

        return "attendance";
        //Put the modelAndView object in the update html. Thereafter iterate the object on the table
    }
    @PostMapping("/attendance")
    public String postAttendance(@ModelAttribute("memberAttend") MemberAttend memberAttend){
        System.out.println("Posted successfully.");
        System.out.println(memberAttend);

        return "attendance";
    }
    @PostMapping("/add")
    public String addNewMember(@ModelAttribute("memberAttend") @Valid MemberCreationDto memberDto,
                               BindingResult result) {
        System.out.println("Entry member");
        Member existing = memberService.findByMobilePhone1(memberDto.getMobilephone1());
        if (existing != null) {
            System.out.println("existing  null: "+existing);
            result.rejectValue("mobilephone1", null, "There is already a member created with that mobile phone.");
        }
        if (result.hasErrors()) {
            System.out.println("result.hasErrors(): "+result.toString());
            return "add";
        }
        memberService.save(memberDto);

        return "redirect:/add?success";
    }

}
