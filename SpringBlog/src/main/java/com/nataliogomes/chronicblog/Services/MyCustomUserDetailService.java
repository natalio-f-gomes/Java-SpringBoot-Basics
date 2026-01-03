package com.nataliogomes.chronicblog.Services;



import com.nataliogomes.chronicblog.Models.AccountModel;
import com.nataliogomes.chronicblog.Models.UserPrincipal;
import com.nataliogomes.chronicblog.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountModel user = repo.findByUsername(username);
        if (user==null){
            System.out.println("USER 404");
            throw new UsernameNotFoundException("USER 404");
        }
        return new UserPrincipal(user);

    }
}
