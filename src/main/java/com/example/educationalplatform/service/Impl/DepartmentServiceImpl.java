package com.example.educationalplatform.service.Impl;

import com.example.educationalplatform.entity.Department;
import com.example.educationalplatform.model.DepartmentModel;
import com.example.educationalplatform.repository.DepartmentRepository;
import com.example.educationalplatform.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentModel save(DepartmentModel departmentModel) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentModel, department);
        departmentRepository.save(department);
        return departmentModel;
    }

    @Override
    public List<DepartmentModel> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentModel> departmentModels = departments.stream().map(dep ->
                        new DepartmentModel(dep.getId(), dep.getName(), dep.getTeacher()))
                .collect(Collectors.toList());
        return departmentModels;
    }

    @Override
    public DepartmentModel getOne(Long id) {
        Department department = departmentRepository.findById(id).get();
        DepartmentModel departmentModel = new DepartmentModel(department.getId(), department.getName(), department.getTeacher());
        return departmentModel;
    }

    @Override
    public DepartmentModel update(Long id, DepartmentModel departmentModel) {
        Department department = departmentRepository.findById(id).get();
        department.setName(departmentModel.getName());
        Department dep = departmentRepository.save(department);
        departmentModel.setId(dep.getId());
        departmentModel.setName(dep.getName());
        departmentModel.setTeachers(dep.getTeacher());
        return departmentModel;
    }

    @Override
    public Boolean delete(Long id) {
        departmentRepository.deleteById(id);
        return true;
    }
}
