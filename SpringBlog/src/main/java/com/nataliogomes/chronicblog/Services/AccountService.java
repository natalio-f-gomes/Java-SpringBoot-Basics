package com.nataliogomes.chronicblog.Services;

import com.nataliogomes.chronicblog.Models.AccountModel;
import com.nataliogomes.chronicblog.Repositories.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AccountModel saveUser(AccountModel newUser) throws Exception {
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        try {
            return accountRepository.save(newUser);
        } catch (Exception e) {
            throw new Exception("Registration failed: " + e.getMessage());
        }
    }


    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }

    public Optional<AccountModel> findByUserById(int id) {
        return accountRepository.findById(id);
    }

    public AccountModel findByUsername(String username) {
        Optional<AccountModel> account = Optional.ofNullable(accountRepository.findByUsername(username));
        return account.orElseThrow(() -> new RuntimeException("User not found with username: " + username));
    }
}