package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.MemberServiceImpl;
import com.jakesajao.Service.UserService;
import com.jakesajao.Service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.Repository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class LoginController{
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private MemberServiceImpl memberServiceImpl;

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/")
    public String viewHomePage(Model model) {

        return findPaginated(1, model);
    }
    //https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        System.out.println("PageNo: "+pageNo);

        User_ user = GetUsername();
        String name = user.getFirstName();
        String email = user.getEmail();
        model.addAttribute("firstName",name);

            Page<Member> page = memberServiceImpl.findPaginated(pageNo, pageSize);
        List<Member>listMembers = page.getContent();

        model.addAttribute("maleTotal", memberServiceImpl.GenderCount("MALE"));
        model.addAttribute("femaleTotal", memberServiceImpl.GenderCount("FEMALE"));
        model.addAttribute("totalTeachers", memberServiceImpl.GenderCount("FEMALE")+
                memberServiceImpl.GenderCount("MALE"));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listMembers", listMembers);
        return "index";
    }

    private String getRole(Collection<Role> roleList){
        for(Role role: roleList) {

            return role.getName();
        }
        return null;
    }
    public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        else
            return null;
    }
    private User_ GetUsername(){
        String currentUserName = null;
        User_ user = null;
        currentUserName = getUsername();
        if (currentUserName!=null)
            user = userRepository.findByEmail(currentUserName);
        else
            throw new NullPointerException();
        return user;
    }

}