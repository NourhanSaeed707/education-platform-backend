package com.example.educationalplatform.model;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.Department;
import com.example.educationalplatform.entity.Student;
import com.example.educationalplatform.entity.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class TeacherModel {
    private Long id;
    private User user;
    private Department department;
    private Course course;
    private List<Student> students;
    private Double salary;
    private String country;
    private String city;

    public TeacherModel() {

    }

    public TeacherModel(Long id, String city, String country, Double salary, User user, Department department, Set<Student> students, List<Course> course) {
    }


    public TeacherModel(Long id, String city, String country, Double salary, User user, Department department) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.salary = salary;
        this.user = user;
        this.department = department;
    }

//    public TeacherModel(Long id, String city, String country, Double salary, String firstName, String lastName, String email, Gender gender, Integer age, String university, Department department, Set<Student> students, List<Course> course) {
//    }
}
