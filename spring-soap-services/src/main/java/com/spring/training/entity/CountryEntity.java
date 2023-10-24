package com.spring.training.entity;

import com.spring.training.model.Currency;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "countries")
public class CountryEntity {
    @Id
    String name;
    String capital;
    int population;
    @Enumerated(EnumType.STRING)
    Currency currency;
}