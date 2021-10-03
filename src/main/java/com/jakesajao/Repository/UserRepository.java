package com.jakesajao.Repository;


import com.jakesajao.Model.User_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_,Long> {
        User_ findByEmail(String email);
    }

