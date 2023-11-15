package com.cydeo.repository;

import com.cydeo.entity.User;
import com.cydeo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
