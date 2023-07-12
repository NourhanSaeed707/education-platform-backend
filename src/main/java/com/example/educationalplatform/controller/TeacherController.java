package com.example.educationalplatform.controller;

import com.example.educationalplatform.model.TeacherModel;
import com.example.educationalplatform.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/teachers/")
@CrossOrigin("*")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TeacherModel>> getAllTeachers() {
        System.out.println("teacher controller");
        List<TeacherModel> teacherModels = teacherService.getAll();
        return ResponseEntity.ok(teacherModels);
    }
    @PostMapping("/add")
    public TeacherModel createTeacher (@RequestBody TeacherModel teacherModel) {
         return teacherService.save(teacherModel);

    }
    @GetMapping("/get/{id}")
    public TeacherModel getOneTeacher(@PathVariable("id") Long id) {
        return  teacherService.getOne(id);
    }
    @PutMapping("/edit/{id}")
    public TeacherModel updateTeacher(@PathVariable("id") Long id,@RequestBody TeacherModel teacherModel) {
        return teacherService.update(id, teacherModel);
    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity<Map<String, Boolean>> deleteTeacher(@PathVariable("id") Long id) {
        Boolean deleted = teacherService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return  ResponseEntity.ok(response);
    }
}
