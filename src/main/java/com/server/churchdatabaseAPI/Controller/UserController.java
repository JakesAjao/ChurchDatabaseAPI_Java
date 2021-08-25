package com.server.churchdatabaseAPI.Controller;

import com.server.churchdatabaseAPI.Model.User_;
import com.server.churchdatabaseAPI.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<User_> GetHello(){
        return this.userService.GetStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody User_ user){
        this.userService.addNewStudent(user);
        System.out.println();
    }

//    @PutMapping(path="{userId}")
//    public void updateUser(@PathVariable("userId")Long userId,
//                              @RequestParam(required = true)String username,
//                              @RequestParam(required = true)String password,
//                           @RequestParam(required = false)String firstName,
//                           @RequestParam(required = false)String surname,
//                           @RequestParam(required = false)String email,
//                           @RequestParam(required = false)String role,
//                           @RequestParam(required = false) LocalDate createdDate,
//                           @RequestParam(required = false)String status) {
//        userService.updateUser(userId,username,password,firstName,surname,email,role,createdDate,status);
//    }

}
