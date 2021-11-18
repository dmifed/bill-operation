package com.dmifed.billoperation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by DIMA, on 18.11.2021
 */
@Entity
@Table(name = "users")
public class UserAccount {
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

    public UserAccount(String name) {
        this(name, null);
    }

    public UserAccount(String name, String email) {
        this.name = name;
        this.email = email;
    }

    protected UserAccount(){}
}
