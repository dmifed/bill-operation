package com.dmifed.billoperation;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by DIMA, on 18.11.2021
 */
@Repository
public interface BillCrudRepository extends CrudRepository<Bill, Long> {
}
