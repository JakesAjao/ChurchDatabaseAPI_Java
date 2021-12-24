package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.LoginControllerUtility;
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

    //https://www.javaguides.net/2020/06/pagination-and-sorting-with-spring-boot-thymeleaf-spring-data-jpa-hibernate-mysql.html

    /*private String getRole(Collection<Role> roleList){
        for(Role role: roleList) {
            return role.getName();
        }
        return null;
    }*/
    /*public String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        else
            return null;
    }*/
    /*private User_ GetUsername(){
        String currentUserName = null;
        User_ user = null;
        currentUserName = getUsername();
        if (currentUserName!=null)
            user = userRepository.findByEmail(currentUserName);
        else
            throw new NullPointerException();
        return user;
    }*/

}