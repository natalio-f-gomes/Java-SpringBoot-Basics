package net.nataliogomes.accountmanagementservice.Controllers;

import net.nataliogomes.accountmanagementservice.Config.JWTService;
import net.nataliogomes.accountmanagementservice.Models.AccountModel;
import net.nataliogomes.accountmanagementservice.Models.Role;
import net.nataliogomes.accountmanagementservice.Services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody AccountModel account) {
        try {

            AccountModel createdAccount = accountService.createAccount(account);
            return ResponseEntity.ok(createdAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AccountModel loginRequest) {
        try {
            AccountModel loggedInAccount = accountService.login(loginRequest);
            String token = jwtService.generateToken(loggedInAccount.getEmail());
            return ResponseEntity.ok(Map.of(
                    "account", loggedInAccount,
                    "token", token
            ));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountModel> getAccount(@PathVariable int id) {
        try {
            return accountService.getAccountById(id)
                    .map(ResponseEntity::ok)
                    .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    @GetMapping("/all")
    public ResponseEntity<?> getAllAccounts() {
        try {
            List<AccountModel> accounts = accountService.getAllAccounts();
            return ResponseEntity.ok(accounts);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable int id, @RequestBody AccountModel accountDetails) {
        try {
            AccountModel updatedAccount = accountService.updateAccount(id, accountDetails);
            return ResponseEntity.ok(updatedAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable int id) {
        try {
            accountService.deleteAccount(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Principal principal) {
        AccountModel account = accountService.getAccountByUsername(principal.getName());
        if (account != null) {
            Map<String, String> profileData = new HashMap<>();
            profileData.put("username", account.getUsername());
            profileData.put("email", account.getEmail());
            return ResponseEntity.ok(profileData);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
    }



}