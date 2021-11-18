package com.dmifed.billoperation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by DIMA, on 18.11.2021
 */
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private long number;

    @Column(nullable = false)
    @Getter
    private User user;

    @Getter
    @Setter
    @Column
    private long balance;

    public Bill(User user, long balance) {
        this.user = user;
        this.balance = balance;
    }

    public Bill(User user) {
        this(user, 0L);
    }
}
