package jooq.demo.com.repository;

import jooq.demo.com.Tables;
import jooq.demo.com.entites.Account;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositiory {

    @Autowired
    private DSLContext dslContext;

    public List<Account> getAccounts() {
        List<Account> queryResults = dslContext.selectFrom(Tables.ACCOUNTS).fetchInto(Account.class);
        return queryResults;
    }
}
