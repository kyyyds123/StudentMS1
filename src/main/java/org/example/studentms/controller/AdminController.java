package org.example.studentms.controller;

import lombok.RequiredArgsConstructor;
import org.example.studentms.entity.StudentInfo;
import org.example.studentms.entity.User;
import org.example.studentms.service.StudentInfoService;
import org.example.studentms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final StudentInfoService studentInfoService;
    private final UserService userService;        // ✅ 注入 UserService

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<StudentInfo> students = studentInfoService.getAllStudents(); // 获取所有学生
        model.addAttribute("students", studentInfoService.getAllStudents());
        return "admin/dashboard";
    }

    @GetMapping("/add-student")
    public String addStudentForm() {
        return "admin/add-student";
    }

    @PostMapping("/add-student")
    public String addStudent(
            @ModelAttribute StudentInfo studentInfo,
            @RequestParam String username, // ✅ 正确：与前端表单字段名一致
            @RequestParam String password  // ✅ 正确：与前端表单字段名一致
    ) {
        System.out.println("Received password: " + password); // 打印日志
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // 明文密码直接存入（按需调整）
        user.setRole("STUDENT");
        userService.register(user);
        studentInfo.setUserId(user.getId());
        studentInfoService.addStudentInfo(studentInfo);
        return "redirect:/admin/dashboard";
    }
    // 显示修改学生表单
    @GetMapping("/edit-student/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {
        StudentInfo student = studentInfoService.getStudentById(id);
        model.addAttribute("student", student);
        return "admin/edit-student"; // 对应模板文件
    }

    // 处理修改请求
    @PostMapping("/edit-student/{id}")
    public String editStudent(@PathVariable Integer id, @ModelAttribute StudentInfo updatedStudent) {
        studentInfoService.updateStudentInfo(updatedStudent);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/delete-student/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentInfoService.deleteStudent(id);
        return "redirect:/admin/dashboard";
    }
}