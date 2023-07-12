package com.example.educationalplatform.service;

import com.example.educationalplatform.model.StudentModel;

import java.util.List;

public interface StudentService {

    StudentModel save(StudentModel studentModel);
    List<StudentModel> getAllStudents();
    StudentModel getOne (Long id);
    StudentModel update(Long id, StudentModel studentModel);
    Boolean delete (Long id);
}
