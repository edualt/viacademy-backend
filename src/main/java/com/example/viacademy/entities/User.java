package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.CourseUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String password;

    @Column(length = 100)
    private String firstName;

    @Column(length = 100)
    private String lastName;

    private Date dateOfBirth;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<CourseUser> courseUsers;

    private Date createdAt;

    private Date updatedAt;

}
