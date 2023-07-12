package com.example.educationalplatform.service;

import com.example.educationalplatform.model.TeacherModel;

import java.util.List;

public interface TeacherService {


    TeacherModel save (TeacherModel teacher);

    List<TeacherModel> getAll ();

    TeacherModel update(Long id, TeacherModel updatedTeacher);

    TeacherModel getOne(Long id);

    Boolean delete(Long id);


}
