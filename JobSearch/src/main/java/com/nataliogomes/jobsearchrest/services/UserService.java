package com.nataliogomes.jobsearchrest.services;


import com.nataliogomes.jobsearchrest.DAO.UserRepo;
import com.nataliogomes.jobsearchrest.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder(12);
    public UserModel saveUser(UserModel user){
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    public void deleteById(int id) {
        userRepo.deleteById(id);
    }

    public Optional<UserModel> findByUserById(int id) {
        return userRepo.findById(id);
    }
}
