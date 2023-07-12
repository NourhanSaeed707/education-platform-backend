package com.example.educationalplatform.controller;

import com.example.educationalplatform.model.DepartmentModel;
import com.example.educationalplatform.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/departments/")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<DepartmentModel>> getAllDepartments() {
        List<DepartmentModel> departmentModels = departmentService.getAllDepartments();
        return ResponseEntity.ok(departmentModels);
    }

    @PostMapping("/add")
    public DepartmentModel createDepartment(@RequestBody DepartmentModel departmentModel) {
        System.out.println("department controller: " );
        return departmentService.save(departmentModel);
    }

    @GetMapping("/get/{id}")
    public  DepartmentModel getOneDepartment(@PathVariable("id") Long id){
        return departmentService.getOne(id);
    }

    @PutMapping("/edit/{id}")
    public DepartmentModel updateDepartment(@PathVariable("id") Long id, @RequestBody DepartmentModel departmentModel) {
        return departmentService.update(id, departmentModel);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String , Boolean>> deleteDepartment(Long id) {
        Boolean deleted = departmentService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return ResponseEntity.ok(response);
    }
}
