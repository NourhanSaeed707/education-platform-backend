package com.example.educationalplatform.service.Impl;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.model.CourseModel;
import com.example.educationalplatform.repository.CourseRepository;
import com.example.educationalplatform.service.CourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        Course course = new Course();
        BeanUtils.copyProperties(courseModel, course);
        courseRepository.save(course);
        return courseModel;
    }

    @Override
    public List<CourseModel> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseModel> courseModels = courses.stream().map(course ->
                        new CourseModel(course.getId(), course.getName(), course.getPrice(), course.getDescription(), course.getDateFrom(), course.getDateTo(), course.getTeacher()))
                .collect(Collectors.toList());
        return courseModels;
    }

    @Override
    public CourseModel getOne(Long id) {
        Course course = courseRepository.findById(id).get();
        CourseModel courseModel = new CourseModel();
        BeanUtils.copyProperties(course, courseModel);
        return courseModel;
    }

    @Override
    public CourseModel update(Long id, CourseModel courseModel) {
        Course course = courseRepository.findById(id).get();
        course.setName(courseModel.getName());
        course.setPrice(courseModel.getPrice());
        course.setDateFrom(courseModel.getDateFrom());
        course.setDateTo(courseModel.getDateTo());
        course.setDescription(courseModel.getDescription());
        courseRepository.save(course);
        return courseModel;
    }

    @Override
    public Boolean delete(Long id) {
        courseRepository.deleteById(id);
        return true;
    }
}
