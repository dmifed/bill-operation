package com.dmifed.billoperation;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by DIMA, on 19.11.2021
 */
public interface AccountCrudRepository extends CrudRepository<Account, Long> {

    List<Account> findAccountsByName(String name);
}
