package com.nataliogomes.chronicblog.Controlllers;

import com.nataliogomes.chronicblog.Models.AccountModel;
import com.nataliogomes.chronicblog.Services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@Slf4j
public class AccountController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AccountService accountService;


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody AccountModel accountModel) {
        try {
            // Set the owner as the account itself (self-referential)
            accountModel.setOwner(accountModel);

            AccountModel savedUser = accountService.saveUser(accountModel);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        try{
            accountService.deleteById(id);
            return ResponseEntity.ok("User Deleted");
        }
        catch (Exception e){
            return ResponseEntity.ok("User Not Found");
        }
    }

    @GetMapping("/user/{id}")
    public Optional<AccountModel> getUser(@PathVariable("id") int id){
        try{
            return accountService.findByUserById(id);
        }
        catch (Exception e){
            return Optional.empty();
        }
    }

    @PutMapping("updateUser/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody AccountModel accountModel) {
        try {
            // Fetch the existing user by id
            Optional<AccountModel> existingAccount = accountService.findByUserById(id);

            if (existingAccount.isPresent()) {
                AccountModel userToUpdate = existingAccount.get();

                // Update only the username and email fields
                userToUpdate.setUsername(accountModel.getUsername());
                userToUpdate.setEmail(accountModel.getEmail()); // Assuming email field exists in AccountModel

                // Save the updated user
                accountService.saveUser(userToUpdate);

                return ResponseEntity.ok("User Updated");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the user");
        }
    }



}
