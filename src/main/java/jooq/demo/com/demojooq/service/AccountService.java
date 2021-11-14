package jooq.demo.com.demojooq.service;

import jooq.demo.com.demojooq.entites.Account;
import jooq.demo.com.demojooq.repository.AccountRepositiory;
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
