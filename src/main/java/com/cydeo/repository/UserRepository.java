package com.cydeo.repository;

import com.cydeo.entity.User;
import com.cydeo.service.UserService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String username);
    @Transactional //Use this annotation for delete and persist
    void deleteByUserName(String username);
}
