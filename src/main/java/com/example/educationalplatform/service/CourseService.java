package com.example.educationalplatform.service;

import com.example.educationalplatform.model.CourseModel;
import com.example.educationalplatform.model.StudentModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CourseService {
    CourseModel save(CourseModel courseModel);
    List<CourseModel> getAllCourses();
    CourseModel getOne (Long id);
    CourseModel update(Long id, CourseModel courseModel);
    Boolean delete (Long id);
}
