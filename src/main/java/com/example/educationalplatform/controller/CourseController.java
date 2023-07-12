package com.example.educationalplatform.controller;

import com.example.educationalplatform.model.CourseModel;
import com.example.educationalplatform.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses/")
public class CourseController {

    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<CourseModel>> getAllCourses() {
        List<CourseModel> courseModels = courseService.getAllCourses();
        return ResponseEntity.ok(courseModels);
    }

    @PostMapping("/add")
    public CourseModel createCourse(@RequestBody CourseModel courseModel) {
        return courseService.save(courseModel);
    }

    @GetMapping("/get/{id}")
    public  CourseModel getOneCourse(@PathVariable("id") Long id){
        return courseService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public CourseModel updateCourse(@PathVariable("id") Long id, @RequestBody CourseModel courseModel) {
        return courseService.update(id, courseModel);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Boolean>> deleteCourse(Long id) {
        Boolean deleted = courseService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
