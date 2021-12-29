package com.jakesajao.Service;
import java.awt.print.Book;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.UserRepository;
import com.jakesajao.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    public User_ findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User_ save(UserRegistrationDto registration) {
        User_ user = new User_(registration.getFirstName(), registration.getLastName()
                ,passwordEncoder.encode(registration.getPassword()),registration.getEmail(), Arrays.asList(new Role("ROLE_ADMIN")), LocalDate.now());

        System.out.println("Save user as : "+user);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Email: "+ email);

        User_ user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }

        String roleName = null;
        List<Role> role = (List<Role>) user.getRoles();
        for(int i=0;i<role.size();i++){
            System.out.println("user.getROLE Name(): "+role.get(i).getName());
            roleName=role.get(i).getName();
        }

        if (roleName.equals("ROLE_ADMIN")) {
            return new org.springframework.security.core.userdetails.User(user.getEmail(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }
        return null;
    }
    public void UpdateUser(User_ user){
        if (user==null){
            System.out.println("User is empty.");
            return;
        }
        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        List<Role> role = new ArrayList<>();
        userRepository.save(user);
        System.out.println("User Saved successfully. User: "+user);
    }
    public User getCurrentUser(Principal principal){
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
    private Collection <?extends GrantedAuthority> mapRolesToAuthorities(Collection<Role>roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }



}


