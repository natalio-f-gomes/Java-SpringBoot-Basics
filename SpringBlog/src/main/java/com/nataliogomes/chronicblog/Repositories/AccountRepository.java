package com.nataliogomes.chronicblog.Repositories;

import com.nataliogomes.chronicblog.Models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Integer> {
    AccountModel findByUsername(String username);
    AccountModel save(AccountModel user);
}
