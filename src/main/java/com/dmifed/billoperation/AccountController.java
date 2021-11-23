package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * Created by DIMA, on 19.11.2021
 */
@RestController
public class AccountController {

    Logger log = Logger.getGlobal();

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    @PostMapping("/account/create")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public Account create(@RequestBody Account account){
        //Account account = new Account(name, email);
        //System.out.println(account.getName() + " " + account.getEmail());
        accountCrudRepository.save(account);
        //log.info("created account " + name);
        return account;
    }

    @PostMapping("/account/update/{accountId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public Account update (@PathVariable long accountId, String email){
        Account account = accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
        account.setEmail(email);
        log.info("update email account " + email);
        accountCrudRepository.save(account);
        return account;
    }

    @GetMapping("/account/{accountId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public Account getAccount (@PathVariable long accountId){
        return accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
    }

}
