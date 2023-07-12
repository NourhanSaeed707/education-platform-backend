package com.example.educationalplatform.service.Impl;

import com.example.educationalplatform.entity.Department;
import com.example.educationalplatform.entity.Enum.Role;
import com.example.educationalplatform.entity.Teacher;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.model.DepartmentModel;
import com.example.educationalplatform.model.TeacherModel;
import com.example.educationalplatform.model.UserModel;
import com.example.educationalplatform.repository.DepartmentRepository;
import com.example.educationalplatform.repository.TeacherRepository;
import com.example.educationalplatform.repository.UserRepository;
import com.example.educationalplatform.service.TeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public TeacherServiceImpl(TeacherRepository teacherRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, DepartmentRepository departmentRepository) {
        this.teacherRepository = teacherRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public TeacherModel save(TeacherModel teacherModel) {
        com.example.educationalplatform.entity.Teacher teacherEntity = new com.example.educationalplatform.entity.Teacher();
        User user = new User();
        user.setRole(Role.TEACHER);
        user.setFirstName(teacherModel.getUser().getFirstName());
        user.setLastName(teacherModel.getUser().getLastName());
        user.setEmail(teacherModel.getUser().getEmail());
        user.setPassword( this.passwordEncoder.encode(teacherModel.getUser().getPassword()));
        user.setAge(teacherModel.getUser().getAge());
        user.setUniversity(teacherModel.getUser().getUniversity());
        user.setGender(teacherModel.getUser().getGender());
        User newUser = userRepository.save(user);
        teacherEntity.setUser(newUser);
        teacherRepository.save(teacherEntity);
        return teacherModel;
    }

    @Override
    public List<TeacherModel> getAll() {
        List<Teacher> teacherEntities = teacherRepository.findAll();
        List<TeacherModel> teachers =  teacherEntities.stream().map(teacher ->
                new TeacherModel(teacher.getId(), teacher.getCity(), teacher.getCountry(), teacher.getSalary(), teacher.getUser(), teacher.getDepartment()))
                .collect(Collectors.toList());
        return teachers;
    }

    @Override
    public TeacherModel update(Long id, TeacherModel updatedTeacher) {
       Teacher teacher = teacherRepository.findById(id).get();
        teacher.setCity(updatedTeacher.getCity());
        teacher.setCountry(updatedTeacher.getCountry());
        teacher.setSalary(updatedTeacher.getSalary());
        User newUser = userRepository.findById(teacher.getUser().getId()).get();
        newUser.setFirstName(updatedTeacher.getUser().getFirstName());
        newUser.setLastName(updatedTeacher.getUser().getLastName());
        newUser.setEmail(updatedTeacher.getUser().getEmail());
        newUser.setPassword(updatedTeacher.getUser().getPassword());
        newUser.setGender(updatedTeacher.getUser().getGender());
        newUser.setUniversity(updatedTeacher.getUser().getUniversity());
        newUser.setAge(updatedTeacher.getUser().getAge());
        userRepository.save(newUser);
        teacher.setUser(newUser);
        teacherRepository.save(teacher);
        return updatedTeacher;
    }

    @Override
    public TeacherModel getOne(Long id) {
        Teacher teacher = teacherRepository.findById(id).get();
        TeacherModel teacherModel = new TeacherModel();
        BeanUtils.copyProperties(teacher, teacherModel);
        UserModel userModel = new UserModel();
        DepartmentModel departmentModel = new DepartmentModel();
        BeanUtils.copyProperties(teacher.getUser(), userModel);
        BeanUtils.copyProperties(teacher.getDepartment(), departmentModel);
        return teacherModel;
    }

    @Override
    public Boolean delete(Long id) {
        teacherRepository.deleteById(id);
        return true;
    }
}
