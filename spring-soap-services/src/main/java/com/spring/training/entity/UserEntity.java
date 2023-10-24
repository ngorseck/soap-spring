package com.spring.training.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    Long id;
    String firstName;
    String lastName;
    @Column(nullable = false, unique = true)
    String userName;
    String password;
}