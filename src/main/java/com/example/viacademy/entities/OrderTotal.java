package com.example.viacademy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Currency;
import java.util.Date;

@Entity
@Table(name = "orderTotals")
@Getter
@Setter
public class OrderTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;

    private Currency currency;

    private Date createdAt;

    private Date updatedAt;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
