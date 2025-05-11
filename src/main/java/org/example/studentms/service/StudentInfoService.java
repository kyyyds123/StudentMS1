package org.example.studentms.service;

import lombok.RequiredArgsConstructor;
import org.example.studentms.entity.StudentInfo;
import org.example.studentms.mapper.StudentInfoMapper;
import org.example.studentms.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentInfoService {
    private final StudentInfoMapper studentInfoMapper;
    private final UserMapper userMapper;


    public void addStudentInfo(StudentInfo studentInfo) {
        studentInfoMapper.insertStudentInfo(studentInfo);
    }

    public List<StudentInfo> getAllStudents() {
        return studentInfoMapper.findAllStudents();
    }

    public StudentInfo getStudentByUserId(Integer userId) {
        return studentInfoMapper.findByUserId(userId);
    }

    public void updateStudent(StudentInfo studentInfo) {
        studentInfoMapper.updateStudentInfo(studentInfo);
    }
    public void updateStudentInfo(StudentInfo studentInfo) {
        studentInfoMapper.updateStudentInfo(studentInfo);
    }

    public StudentInfo getStudentById(Integer id) {
        return studentInfoMapper.findById(id);
    }

    @Transactional
    public void deleteStudent(Integer studentId) {
        StudentInfo student = studentInfoMapper.findById(studentId);
        if (student != null) {
            userMapper.deleteUser(student.getUserId());
            studentInfoMapper.deleteStudentInfo(studentId);
        }
    }
}