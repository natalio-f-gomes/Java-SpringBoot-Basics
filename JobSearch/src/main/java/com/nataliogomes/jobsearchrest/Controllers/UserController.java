package com.nataliogomes.jobsearchrest.Controllers;


import com.nataliogomes.jobsearchrest.models.UserModel;
import com.nataliogomes.jobsearchrest.services.JWTService;
import com.nataliogomes.jobsearchrest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    JWTService jwtService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @PostMapping("user/register")
    public ResponseEntity<String> register(@RequestBody UserModel user){
        userService.saveUser(user);
        return ResponseEntity.ok("User Registered successfully!");
    }

    @DeleteMapping("user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        try{
            userService.deleteById(id);
            return ResponseEntity.ok("User Deleted");
        }
        catch (Exception e){
            return ResponseEntity.ok("User Not Found");
        }
    }

    @GetMapping("user/{id}")
    public Optional<UserModel> getUser(@PathVariable("id") int id){
        try{
            return userService.findByUserById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody UserModel userModel){
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(userModel.getUsername(), userModel.getPassword())
        );
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userModel.getUsername());
        }else{
            return "Login Failed";
        }
    }
}
