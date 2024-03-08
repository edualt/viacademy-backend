package com.example.viacademy.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "consultancies")
@Getter
@Setter
public class Consultancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String title;

    @Column(length = 255)
    private String description;

    private Boolean isDone;

    private Date dateOfConsultancy;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

//    TODO: Relation with user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Date createdAt;

    private Date updatedAt;

}
