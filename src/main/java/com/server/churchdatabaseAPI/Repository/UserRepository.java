package com.server.churchdatabaseAPI.Repository;

import com.server.churchdatabaseAPI.Model.User_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User_,Long> {
    @Query("SELECT s FROM User_ s WHERE s.Email = ?1")
    Optional<User_> findUserByEmail(String email);
}
