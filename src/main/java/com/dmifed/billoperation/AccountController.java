package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * Created by DIMA, on 19.11.2021
 */
@RestController
public class AccountController {

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    @PostMapping("/account/create")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public String create(@RequestParam String name){
        Account account = new Account(name);
        accountCrudRepository.save(account);
        return "creation success";
    }

    @PostMapping("/account/{accountId}/update")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public String update (@PathVariable long accountId, String email){
        Account account = accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
        account.setEmail(email);
        return "update success";
    }

    @GetMapping("/account/{accountId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public Account getAccount (@PathVariable long accountId){
        return accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/account/{accountId}/delete")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public String delete (@PathVariable long accountId){
        accountCrudRepository.deleteById(accountId);
        return "account has been deleted";
    }

}