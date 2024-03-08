package com.example.viacademy.entities;

import com.example.viacademy.entities.pivots.CourseCategory;
import com.example.viacademy.entities.pivots.CourseUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 255)
    private String description;

//    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
//    private List<CourseUser> courseUsers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<CourseCategory> courseCategories;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Video> videos;

    private Date createdAt;

    private Date updatedAt;

}
