package com.rohit.ecommerce_backend.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String Address;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
