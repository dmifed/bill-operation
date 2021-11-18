package com.dmifed.billoperation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

/**
 * Created by DIMA, on 18.11.2021
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    @Column(nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(unique = true)
    private String email;

    public User(String name) {
        this(name, null);
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
