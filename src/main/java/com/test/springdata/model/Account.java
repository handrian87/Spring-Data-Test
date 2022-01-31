package com.test.springdata.model;

import org.springframework.data.annotation.Id;

import java.math.BigDecimal;


public class Account {
    @Id
    private long id;
    private String name;
    private BigDecimal amount;
}
