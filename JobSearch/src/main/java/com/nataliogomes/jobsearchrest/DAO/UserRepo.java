package com.nataliogomes.jobsearchrest.DAO;

import com.nataliogomes.jobsearchrest.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

public interface UserRepo extends JpaRepository<UserModel, Integer> {
    UserModel findByUsername(String username);
    UserModel save(UserModel user);
}
