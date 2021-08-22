package com.server.churchdatabaseAPI.Service;

import com.server.churchdatabaseAPI.Model.User_;
import com.server.churchdatabaseAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    public final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User_> GetStudents(){
        return this.userRepository.findAll();
    }
}
