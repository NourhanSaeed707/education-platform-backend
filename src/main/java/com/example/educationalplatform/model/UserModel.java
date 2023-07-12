package com.example.educationalplatform.model;

import com.example.educationalplatform.entity.Enum.Gender;
import com.example.educationalplatform.entity.Enum.Role;
import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer age;
    private String university;
    private Gender gender;
    private Role role;


}
