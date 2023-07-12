package com.example.educationalplatform.service.Impl;

import com.example.educationalplatform.entity.Course;
import com.example.educationalplatform.entity.Enum.Role;
import com.example.educationalplatform.entity.Student;
import com.example.educationalplatform.entity.Teacher;
import com.example.educationalplatform.entity.User;
import com.example.educationalplatform.model.StudentModel;
import com.example.educationalplatform.model.UserModel;
import com.example.educationalplatform.repository.StudentRepository;
import com.example.educationalplatform.repository.UserRepository;
import com.example.educationalplatform.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private UserRepository userRepository;

    public StudentServiceImpl(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public StudentModel save(StudentModel studentModel) {
        Student student = new Student();
        User newUser = new User();
        newUser.setFirstName(studentModel.getUser().getFirstName());
        newUser.setLastName(studentModel.getUser().getLastName());
        newUser.setEmail(studentModel.getUser().getEmail());
        newUser.setPassword(studentModel.getUser().getPassword());
        newUser.setGender(studentModel.getUser().getGender());
        newUser.setUniversity(studentModel.getUser().getUniversity());
        newUser.setAge(studentModel.getUser().getAge());
        newUser.setRole(Role.STUDENT);
        User user = userRepository.save(newUser);
        student.setUser(user);
        student.setGraduation(studentModel.getGraduation());
        student.setCity(studentModel.getCity());
        student.setCountry(studentModel.getCountry());
        studentRepository.save(student);
        return studentModel;
    }

    @Override
    public List<StudentModel> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentModel> studentModels = students.stream().map(student ->
                new StudentModel(student.getId(), student.getCity(), student.getCountry(), student.getGraduation(), student.getUser(), student.getCourses(), student.getTeachers()))
                .collect(Collectors.toList());
        return studentModels;
    }

    @Override
    public StudentModel getOne(Long id) {
        Student student = studentRepository.findById(id).get();
        StudentModel studentModel = new StudentModel();
        BeanUtils.copyProperties(student, studentModel);
        return studentModel;
    }

    @Override
    public StudentModel update(Long id, StudentModel studentModel) {
        Student student = studentRepository.findById(id).get();
        student.setCity(studentModel.getCity());
        student.setCountry(studentModel.getCountry());
        student.setGraduation(studentModel.getGraduation());
        User newUser =  new User();
        newUser.setFirstName(studentModel.getUser().getFirstName());
        newUser.setLastName(studentModel.getUser().getLastName());
        newUser.setEmail(studentModel.getUser().getEmail());
        newUser.setPassword(studentModel.getUser().getPassword());
        newUser.setGender(studentModel.getUser().getGender());
        newUser.setUniversity(studentModel.getUser().getUniversity());
        newUser.setAge(studentModel.getUser().getAge());
        newUser.setRole(Role.STUDENT);
        userRepository.save(newUser);
        student.setUser(newUser);
        studentRepository.save(student);
        return studentModel;
    }

    @Override
    public Boolean delete(Long id) {
        studentRepository.deleteById(id);
        return true;
    }
}
