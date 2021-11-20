package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by DIMA, on 18.11.2021
 */
@RestController
public class BillOperationController {

    @Autowired
    private BillCrudRepository billCrudRepository;

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    @GetMapping("/bill/withdrawal/{billId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public Bill withdrawal (@PathVariable long billId, long amount){
        Bill bill = billCrudRepository.findById(billId).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        if(balance >= amount){
            bill.setBalance(balance-amount);
        }
        return bill;
    }

    @GetMapping("/bill/refill/{billId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public Bill refill (@PathVariable long billId, long amount){
        Bill bill = billCrudRepository.findById(billId).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        bill.setBalance(balance+amount);
        return bill;
    }
    @PostMapping("/bill/create")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public String create(@RequestParam long number, @RequestParam long accountId){
        accountCrudRepository.findById(accountId).orElseThrow(NoSuchElementException::new);
        Bill bill = new Bill(number, accountId);
        billCrudRepository.save(bill);
        return "creation success";
    }

    @GetMapping("/bill/balance/{billId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public long getBalance (@PathVariable long billId){
        Bill bill = billCrudRepository.findById(billId).orElseThrow(NoSuchElementException::new);
        return bill.getBalance();
    }
}
