package org.example.studentms.mapper;

import org.apache.ibatis.annotations.*;
import org.example.studentms.entity.StudentInfo;

import java.util.List;

@Mapper
public interface StudentInfoMapper {
    @Insert("INSERT INTO student_info(user_id, name, gender, age, class_name, email, phone) " +
            "VALUES(#{userId}, #{name}, #{gender}, #{age}, #{className}, #{email}, #{phone})")
    void insertStudentInfo(StudentInfo studentInfo);

    @Select("SELECT * FROM student_info")
    List<StudentInfo> findAllStudents();

    @Select("SELECT * FROM student_info WHERE user_id = #{userId}")
    StudentInfo findByUserId(Integer userId);

    @Update("UPDATE student_info SET name=#{name}, gender=#{gender}, age=#{age}, " +
            "class_name=#{className}, email=#{email}, phone=#{phone} WHERE id=#{id}")
    void updateStudentInfo(StudentInfo studentInfo);

    @Delete("DELETE FROM student_info WHERE id = #{studentId}")
    void deleteStudentInfo(Integer studentId);

    @Select("SELECT * FROM student_info WHERE id = #{id}")
    StudentInfo findById(Integer id);
}