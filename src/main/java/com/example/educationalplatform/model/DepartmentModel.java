package com.example.educationalplatform.model;

import com.example.educationalplatform.entity.Teacher;
import lombok.Data;

import java.util.List;

@Data
public class DepartmentModel {

    private Long id;

    private String name;

    private List<Teacher> teachers;

    public DepartmentModel(Long id, String name, List<Teacher> teachers) {
        this.id = id;
        this.name = name;
        this.teachers = teachers;
    }

    public DepartmentModel() {

    }
}
