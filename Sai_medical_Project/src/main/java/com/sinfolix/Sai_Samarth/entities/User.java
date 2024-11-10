package com.sinfolix.Sai_Samarth.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "new_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String roles;
}
