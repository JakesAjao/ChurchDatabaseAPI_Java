package com.jakesajao.Service;

import com.jakesajao.Model.User_;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;
@Service
public interface UserService extends UserDetailsService {
    User_ findByEmail(String email);
    User_ save(UserRegistrationDto registration);
    UserDetails loadUserByUsername(String email);
    void UpdateUser(User_ user);
}
//public class UserService {
//    public final UserRepository userRepository;
//
//    @Autowired
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    User_ save(UserRegistrationDto registration);
//    UserDetails loadUserByUsername(String email);
//    void UpdateUser(User_ user);
//    public List<User_> GetStudents() {
//        return this.userRepository.findAll();
//    }
//
//    public void addNewStudent(User_ user) {
//        Optional<User_> userByEmail = userRepository.findUserByEmail(user.getEmail());
//        if (userByEmail.isPresent()) {
//            throw new IllegalStateException("Email Taken.");
//        }
//        this.userRepository.save(user);
//        //System.out.println(student);
//    }

