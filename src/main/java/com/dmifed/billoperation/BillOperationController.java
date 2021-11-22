package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * Created by DIMA, on 18.11.2021
 */
@RestController
public class BillOperationController {

    Logger log = Logger.getGlobal();

    @Autowired
    private BillCrudRepository billCrudRepository;

    @Autowired
    private AccountCrudRepository accountCrudRepository;

    @GetMapping("/bill/withdrawal/{billId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public Bill withdrawal (@PathVariable @RequestParam long id, @RequestParam long amount) throws NotEnoughMoneyException{
        Bill bill = billCrudRepository.findById(id).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        if(balance >= amount){
            bill.setBalance(balance-amount);
        }else {
            log.warning("Not enough money");
            throw new NotEnoughMoneyException();
        }
        return bill;
    }

    @GetMapping("/bill/refill/{billId}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public Bill refill (@PathVariable @RequestParam long id, @RequestParam long amount){
        Bill bill = billCrudRepository.findById(id).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        bill.setBalance(balance+amount);
        return bill;
    }
    @PostMapping("/bill/create")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public String create(@RequestParam long number, @RequestParam long user){
        accountCrudRepository.findById(user).orElseThrow(NoSuchElementException::new);
        Bill bill = new Bill(number, user);
        billCrudRepository.save(bill);
        return "creation success";
    }

    @GetMapping("/bill/balance/{id}/")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED)
    public long getBalance (@PathVariable long id){
        Bill bill = billCrudRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return bill.getBalance();
    }
}
