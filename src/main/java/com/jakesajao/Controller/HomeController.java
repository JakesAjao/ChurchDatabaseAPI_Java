package com.jakesajao.Controller;

import com.jakesajao.Model.Member;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
import com.jakesajao.dto.MemberCreationDto;
import com.jakesajao.dto.UserRegistrationDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String index(){
        return "index";
    }

    @GetMapping("/login?logout")
    public String logout(){
        System.out.println("Log out...1");
        return "login";
    }

}
