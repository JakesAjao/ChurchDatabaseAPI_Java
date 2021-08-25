package com.server.churchdatabaseAPI.Service;

import com.server.churchdatabaseAPI.Model.User_;
import com.server.churchdatabaseAPI.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserService {
    public final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User_> GetStudents(){
        return this.userRepository.findAll();
    }
    public void addNewStudent(User_ user) {
        Optional<User_> userByEmail  = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("Email Taken.");
       }
        this.userRepository.save(user);
        //System.out.println(student);
    }

//    @Transactional
//    public void updateUser(Long userId,String username, String password, String firstName, String surname, String email,
//                           String role, LocalDate createdDate, String status) {
//        User_ user = userRepository.findById(userId)
//                .orElseThrow(() -> new IllegalStateException(
//                        "user with the id "+userId +" does not exist."));
//        if (username != null &&
//                username.length()>0 &&
//                !Objects.equals(user.getUsername(),username)){
//            user.setUsername(username);
//        }
//        if (password != null &&
//                password.length()>0 &&
//                !Objects.equals(user.getPassword(),password)){
//            user.setPassword(password);
//        }
//        if (role != null &&
//                role.length()>0 &&
//                !Objects.equals(user.getRole(),role)){
//            user.setPassword(role);
//        }
//        if (surname!= null &&
//                surname.length()>0 &&
//                !Objects.equals(user.getSurname(),surname)){
//            user.setSurname(surname);
//        }
//        if (status != null &&
//                status.length()>0 &&
//                !Objects.equals(user.getStatus(),status)){
//            user.setStatus(status);
//        }
//        if (firstName != null &&
//                firstName.length()>0 &&
//                !Objects.equals(user.getFirstName(),firstName)) {
//            user.setFirstName(firstName);
//        }
//        if (email != null &&
//                email.length()>0 &&
//                !Objects.equals(user.getEmail(),email)){
//
//            Optional<User_> userOptional = userRepository.findUserByEmail(email);
//            if (userOptional.isPresent()){
//                throw new IllegalStateException("email Taken");
//            }
//
//            user.setEmail(email);
//        }
//    }
}