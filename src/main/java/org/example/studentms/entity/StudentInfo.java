package org.example.studentms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentInfo {
    private Integer id;
    private Integer userId;
    private String name;
    private String gender;
    private Integer age;
    private String className;
    private String email;
    private String phone;
}