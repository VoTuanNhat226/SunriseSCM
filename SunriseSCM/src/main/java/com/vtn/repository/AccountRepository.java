package com.vtn.repository;

import com.vtn.pojo.Account;

public interface AccountRepository {
    Account findByUsername(String username);
    void saveOrUpdate(Account account);
}
