package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            propagation = Propagation.REQUIRED)
    public ResponseEntity<Account> create(@RequestBody Account account){
        try {
            return new ResponseEntity<>(accountCrudRepository.save(account), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
    }

    //http://localhost:8080/account/update/16/
    //"vas2mail@mail_upd"
    @PutMapping("/account/update/{accountId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public ResponseEntity<Account> update (@PathVariable long accountId, @RequestBody String email){
        Account account = accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
        account.setEmail(email);
        try {
            return new ResponseEntity<>(accountCrudRepository.save(account), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //http://localhost:8080/account/1/
    @GetMapping("/account/{accountId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public ResponseEntity<Account> getAccount (@PathVariable long accountId){
        Account account = accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
        try {
            return new ResponseEntity<>(account, HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
