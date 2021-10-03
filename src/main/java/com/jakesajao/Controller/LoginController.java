package com.jakesajao.Controller;

import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.Service.UserService;
import com.jakesajao.Service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/admin")
    public ModelAndView admin() {
        String currentUserName = null;
        User_ gitUser = null;
        currentUserName = getUsername();
        if (currentUserName != null)
            gitUser = userRepository.findByEmail(currentUserName);
        else
            throw new NullPointerException();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        String name = gitUser.getFirstName() + " " + gitUser.getLastName();
        String email = gitUser.getEmail();
        //List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
//        modelAndView.addObject("repolist",repoList);
//        modelAndView.addObject("gituser",name);
//        modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());

        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView home() {
//        String currentUserName = null;
//        User gitUser = null;
//        currentUserName = getUsername();
//        if (currentUserName!=null)
//            gitUser = userRepository.findByEmail(currentUserName);
//        else
//            throw new NullPointerException();
//        String roleVal = getRole(gitUser.getRoles());
//
//        if (roleVal.equals("ROLE_ADMIN")){
//            List<GitUser> userList = userRepository.findAll();
//            List<GitUser> userList2  = getUserList(userList);
//
           ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ahome");
//            String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
//            String email = gitUser.getEmail();
//            modelAndView.addObject("userList",userList2);
//            modelAndView.addObject("gituser",name);
//            modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());

        return modelAndView;

    //}

//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("home");
//        String name = gitUser.getFirstName() +" "+ gitUser.getLastName();
//        String email = gitUser.getEmail();
//
//        List<Repository> repoList = httpconnections.getUserRepo(gitUser.getFirstName()+gitUser.getLastName());
//        System.out.println("repoList: " + repoList);
//        modelAndView.addObject("repolist",repoList);
//        modelAndView.addObject("gituser",name);
//        modelAndView.addObject("git",gitUser.getFirstName()+gitUser.getLastName());
//
//        return modelAndView;
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
    private List<User_> getUserList(List<User_> userList){
        List<User_> userList2 = new ArrayList<>();
        for(var user:userList){
            System.out.println("User role: " + user.getRole());
            String roleVal2 = null;
            for(Role role:user.getRoles()){
                roleVal2 = role.getName();
            }
            System.out.println("User role name 2: " +  roleVal2);

            User_ userData = new User_(user.getId(),user.getFirstName(),user.getLastName(),user.getMobilephone(),
                    user.getEmail(),user.getRole(),user.getCreatedDate(),user.getBirthdate(),user.getGender(),roleVal2);
            userList2.add(userData);
        }
        return userList2;
    }

}