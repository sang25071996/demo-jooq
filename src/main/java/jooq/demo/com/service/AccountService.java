package jooq.demo.com.service;

import jooq.demo.com.entites.Account;
import jooq.demo.com.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepositiory;

    public AccountService(AccountRepository accountRepositiory) {
        this.accountRepositiory = accountRepositiory;
    }

    public List<Account> getAccounts() {
        return this.accountRepositiory.getAccounts();
    }
}
