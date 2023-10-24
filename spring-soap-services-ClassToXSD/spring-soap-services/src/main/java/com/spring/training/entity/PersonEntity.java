package com.spring.training.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "persons")
public class PersonEntity {
    @Id
    Long id;
    String firstName;
    String lastName;
}