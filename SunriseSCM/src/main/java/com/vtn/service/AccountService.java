package com.vtn.service;

import com.vtn.pojo.Account;

public interface AccountService {
    Account findByUsername(String username);
    void saveOrUpdate(Account account);
}
