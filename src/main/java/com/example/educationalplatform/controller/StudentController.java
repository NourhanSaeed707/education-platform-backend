package com.example.educationalplatform.controller;

import com.example.educationalplatform.model.StudentModel;
import com.example.educationalplatform.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<StudentModel>> getAllStudents() {
        List<StudentModel> studentModels = studentService.getAllStudents();
        return ResponseEntity.ok(studentModels);
    }

    @PostMapping("/add")
    public StudentModel createStudents(@RequestBody StudentModel studentModel) {
        return studentService.save( studentModel);
    }

    @GetMapping("/get/{id}")
    public  StudentModel getOneStudent(@PathVariable("id") Long id){
        return studentService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public StudentModel updateStudent(@PathVariable("id") Long id, @RequestBody StudentModel studentModel) {
        return studentService.update(id, studentModel);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Boolean>> deleteStudent(Long id) {
        Boolean deleted = studentService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
