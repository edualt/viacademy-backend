package com.example.viacademy.entities.pivots;

import com.example.viacademy.entities.Category;
import com.example.viacademy.entities.Consultancy;
import com.example.viacademy.entities.Course;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "courseConsultancies")
@Getter
@Setter
public class CourseConsultancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;

    @ManyToOne
    @JoinColumn(name = "consultancy_id")
    @JsonBackReference
    private Consultancy consultancy;

}
