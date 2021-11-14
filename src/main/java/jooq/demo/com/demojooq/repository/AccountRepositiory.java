package jooq.demo.com.demojooq.repository;

import static jooq.demo.com.demojooq.Tables.ACCOUNTS;

import java.util.List;
import jooq.demo.com.demojooq.entites.Account;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountRepositiory {

    @Autowired
    private DSLContext dslContext;

    public List<Account> getAccounts() {
        return dslContext.selectFrom(ACCOUNTS).fetchInto(Account.class);
    }
}
