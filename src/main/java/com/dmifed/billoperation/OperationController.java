package com.dmifed.billoperation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by DIMA, on 18.11.2021
 */
@RestController
public class OperationController {

    @GetMapping("/operations/{billId}")
    public void withdrawal (@PathVariable String billId, long amount){

    }

}
