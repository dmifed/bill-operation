package com.dmifed.billoperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Created by DIMA, on 18.11.2021
 */
@RestController
public class OperationController {

    @Autowired
    private BillCrudRepository billCrudRepository;

    @GetMapping("/operations/{billId}/withdrawal")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public void withdrawal (@PathVariable long billId, long amount){
        Bill bill = billCrudRepository.findById(billId).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        if(balance >= amount){
            bill.setBalance(balance-amount);
        }

    }

    @GetMapping("/operations/{billId}/refill")
    @Transactional(isolation = Isolation.READ_COMMITTED,
            propagation = Propagation.REQUIRED,
            rollbackFor = RollbackException.class)
    public void refill (@PathVariable long billId, long amount){
        Bill bill = billCrudRepository.findById(billId).orElseThrow(NoSuchElementException::new);
        long balance = bill.getBalance();
        bill.setBalance(balance+amount);
    }

}
