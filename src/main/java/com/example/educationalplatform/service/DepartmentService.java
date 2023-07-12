package com.example.educationalplatform.service;

import com.example.educationalplatform.model.DepartmentModel;
import com.example.educationalplatform.model.StudentModel;

import java.util.List;

public interface DepartmentService {

    DepartmentModel save(DepartmentModel departmentModel);
    List<DepartmentModel> getAllDepartments();
    DepartmentModel getOne (Long id);
    DepartmentModel update(Long id, DepartmentModel departmentModel);
    Boolean delete (Long id);
}
