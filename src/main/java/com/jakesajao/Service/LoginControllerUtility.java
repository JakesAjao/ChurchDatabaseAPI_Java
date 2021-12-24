package com.jakesajao.Service;

import com.jakesajao.Model.Role;
import com.jakesajao.Model.User_;
import com.jakesajao.Repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class LoginControllerUtility {
    public String getAuthUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        else
            return null;
    }
    public User_ GetUsername(UserRepository userRepository){
        String currentUserName = null;
        User_ user = null;
        currentUserName = getAuthUsername();
        if (currentUserName!=null)
            user = userRepository.findByEmail(currentUserName);
        else
            user = new User_();
        return user;
    }
    private String getRole(Collection<Role> roleList){
        for(Role role: roleList) {
            return role.getName();
        }
        return null;
    }
}
