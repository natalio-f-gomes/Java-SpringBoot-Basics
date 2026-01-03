package com.nataliogomes.jobsearchrest.services;


import com.nataliogomes.jobsearchrest.DAO.UserRepo;
import com.nataliogomes.jobsearchrest.models.UserModel;
import com.nataliogomes.jobsearchrest.models.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyCustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = repo.findByUsername(username);
        if (user==null){
            System.out.println("USER 404");
            throw new UsernameNotFoundException("USER 404");
        }
        return new UserPrincipal(user);

    }
}
