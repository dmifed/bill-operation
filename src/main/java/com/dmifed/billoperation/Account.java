package com.dmifed.billoperation;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by DIMA, on 18.11.2021
 */
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    public Account(String name) {
        this(name, null);
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected Account(){}
}
