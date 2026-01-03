package net.nataliogomes.accountmanagementservice.Repositories;

import net.nataliogomes.accountmanagementservice.Models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountModel, Integer> {
    AccountModel findByUsername(String username);

    AccountModel findByEmail(String email);
}
