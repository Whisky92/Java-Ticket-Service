package com.epam.training.ticketservice.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(String username, String password, Role role) {
        this.username=username;
        this.password=password;
        this.role=role;
    }

    public enum Role {
        ADMIN,
        USER
    }

}
