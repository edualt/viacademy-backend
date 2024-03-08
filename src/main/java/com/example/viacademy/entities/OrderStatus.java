package com.example.viacademy.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orderStatus")
@Getter
@Setter
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, unique = true)
    private String name;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Order> orders;

}