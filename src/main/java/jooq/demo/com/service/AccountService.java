package jooq.demo.com.service;

import jooq.demo.com.entites.Account;
import jooq.demo.com.repository.AccountRepositiory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepositiory accountRepositiory;

    public AccountService(AccountRepositiory accountRepositiory) {
        this.accountRepositiory = accountRepositiory;
    }

    public List<Account> getAccounts() {
        return this.accountRepositiory.getAccounts();
    }
}
