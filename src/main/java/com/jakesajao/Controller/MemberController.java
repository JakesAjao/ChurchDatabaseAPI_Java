package com.jakesajao.Controller;


import com.jakesajao.Model.Member;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
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
import java.util.List;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @ModelAttribute("member")
    public MemberCreationDto memberCreationDto() {
        return new MemberCreationDto();
    }

    @GetMapping("/add")
    public String addMember(Model model){
        return "add";
    }

    @GetMapping("/attendance")
    public ModelAndView updateMember() {
        //return "update";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("attendance");

        List<Member> memberList = memberRepository.findAll();
        modelAndView.addObject("memberList",memberList);
        return modelAndView;
        //Put the modelAndView object in the update html. Thereafter iterate the object on the table
    }
    /*@GetMapping("/update")
    public ModelAndView updateMember() {
        //return "update";
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("update");

        List<Member> memberList = memberRepository.findAll();
        modelAndView.addObject("memberList",memberList);
        return modelAndView;
        //Put the modelAndView object in the update html. Thereafter iterate the object on the table
    }*/

    @PostMapping("/add")
    public String addNewMember(@ModelAttribute("member") @Valid MemberCreationDto memberDto,
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
