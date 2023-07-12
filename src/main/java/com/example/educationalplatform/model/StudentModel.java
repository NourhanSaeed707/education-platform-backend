package com.example.educationalplatform.model;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.Enum.Gender;
import com.example.educationalplatform.entity.Enum.Graduation;
import com.example.educationalplatform.entity.Teacher;
import com.example.educationalplatform.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class StudentModel {
    private Long id;
    private User user;
    private Set<Teacher> teachers;

    private Set<Course> courses;
    private Graduation graduation;
    private String country;
    private String city;

    public StudentModel(Long id, String city, String country, Graduation graduation, User user, Set<Course> courses, Set<Teacher> teachers) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.graduation = graduation;
        this.user = user;
        this.courses =  courses;
        this.teachers =  teachers;
    }

    public StudentModel() {

    }
}
