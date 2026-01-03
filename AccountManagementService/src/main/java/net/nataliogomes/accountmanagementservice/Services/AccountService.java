package net.nataliogomes.accountmanagementservice.Services;

import net.nataliogomes.accountmanagementservice.Models.AccountModel;
import net.nataliogomes.accountmanagementservice.Models.Role;
import net.nataliogomes.accountmanagementservice.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public AccountModel createAccount(AccountModel account) {
        if (accountRepository.findByEmail(account.getEmail()) != null) {
            throw new RuntimeException("Email already exists");
        }
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }
        if(account.getRole().equals(Role.ADMIN)){
            throw new RuntimeException("Only 1 Admin allowed");
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional<AccountModel> getAccountById(int id) {
        return accountRepository.findById(id);
    }

    public List<AccountModel> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Transactional
    public AccountModel updateAccount(int id, AccountModel accountDetails) {
        return accountRepository.findById(id).map(account -> {
            if (!account.getUsername().equals(accountDetails.getUsername()) &&
                    accountRepository.findByUsername(accountDetails.getUsername()) != null) {
                throw new RuntimeException("Username already exists");
            }
            if (!account.getEmail().equals(accountDetails.getEmail()) &&
                    accountRepository.findByEmail(accountDetails.getEmail()) != null) {
                throw new RuntimeException("Email already exists");
            }
            account.setUsername(accountDetails.getUsername());
            account.setEmail(accountDetails.getEmail());
            if (accountDetails.getPassword() != null && !accountDetails.getPassword().isEmpty()) {
                account.setPassword(passwordEncoder.encode(accountDetails.getPassword()));
            }
            account.setRole(accountDetails.getRole());
            return accountRepository.save(account);
        }).orElseThrow(() -> new RuntimeException("Account not found with id " + id));
    }

    @Transactional
    public void deleteAccount(int id) {
        accountRepository.deleteById(id);
    }

    public AccountModel findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public AccountModel findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public AccountModel login(AccountModel account) {
        AccountModel foundAccount = accountRepository.findByEmail(account.getEmail());

        if (foundAccount == null) {
            throw new RuntimeException("Account not found");
        }

        if (!passwordEncoder.matches(account.getPassword(), foundAccount.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // At this point, authentication is successful
        // You might want to do additional things here, like creating a session or generating a token

        // Return the account without the password for security reasons
        foundAccount.setPassword(null);
        return foundAccount;
    }

    public AccountModel getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

}