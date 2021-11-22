package com.dmifed.billoperation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by DIMA, on 18.11.2021
 */
@Repository
public interface BillCrudRepository extends CrudRepository<Bill, Long> {
    Optional<Bill> findBillByNumber(long number);
    List<Bill> findBillByUser(long id);

}
