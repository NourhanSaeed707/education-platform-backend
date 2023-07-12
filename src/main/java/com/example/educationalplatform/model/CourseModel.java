package com.example.educationalplatform.model;
import com.example.educationalplatform.entity.Teacher;
import lombok.Data;

import java.util.Date;

@Data
public class CourseModel {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Date dateFrom;
    private Date dateTo;

    private Teacher teacher;

    public CourseModel(Long id, String name, Double price, String description, Date dateFrom, Date dateTo, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.teacher = teacher;
    }

    public CourseModel() {

    }
}
