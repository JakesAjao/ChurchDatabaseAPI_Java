package com.jakesajao.Controller;


import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.AttendanceService;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
import com.jakesajao.dto.*;
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
    private AttendanceServiceImpl attendanceServiceImpl;

    @ModelAttribute("memberCreationDto")
    public MemberCreationDto memberCreationDto() {
        return new MemberCreationDto();
    }
    public MemberController(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }
    @GetMapping("/add")
    public String addMember(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String addNewMember(@ModelAttribute @Valid MemberCreationDto memberCreationDto,
                               BindingResult result,Model model) {
        System.out.println("Entry member");
        Member existing = memberService.findByMobilePhone1(memberCreationDto.getMobilephone1());
        if (existing != null) {
            System.out.println("existing  null: "+existing);
            String msg = "There is already a member created with that mobile phone.";
            result.rejectValue("mobilephone1", null, msg);
            model.addAttribute("error",msg);
            return "add";
        }
        if (result.hasErrors()) {
            System.out.println("result.hasErrors(): "+result.toString());
            model.addAttribute("error1","Error: "+result.toString());
            return "add";
        }
        memberService.save(memberCreationDto);
        model.addAttribute("success","You've successfully updated the attendance!");

        return "add";

    }
}
