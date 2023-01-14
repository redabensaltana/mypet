package com.redabens.mypet.Repository;

import com.redabens.mypet.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository <User, Integer> {
    User findUserByEmail(String email);
}
