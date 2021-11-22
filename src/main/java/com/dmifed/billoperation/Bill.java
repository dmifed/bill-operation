package com.dmifed.billoperation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by DIMA, on 18.11.2021
 */
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long id;

    @Column(nullable = false)
    @Getter
    private long  number;

    @Column(nullable = false)
    @Getter
    private long user;

    @Getter
    @Setter
    @Column
    private long balance;

    public Bill(long number, long user) {
        this.number = number;
        this.user = user;
        this.balance = 0L;
    }

    protected Bill(){}

/*    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return number == bill.number &&
                user == bill.user &&
                balance == bill.balance;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, user, balance);
    }*/
}
