package net.nataliogomes.accountmanagementservice.Services;

import net.nataliogomes.accountmanagementservice.Models.AccountModel;
import net.nataliogomes.accountmanagementservice.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Collections;

@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AccountModel account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                account.getEmail(), account.getPassword(), getAuthorities(account));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(AccountModel account) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + account.getRole().name()));
    }
}
