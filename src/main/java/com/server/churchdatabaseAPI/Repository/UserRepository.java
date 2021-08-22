package com.server.churchdatabaseAPI.Repository;

import com.server.churchdatabaseAPI.Model.User_;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User_,Long> {
}
