package org.ky.studentms.controller;

import lombok.RequiredArgsConstructor;
import org.ky.studentms.entity.StudentInfo;
import org.ky.studentms.entity.User;
import org.ky.studentms.service.StudentInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentInfoService studentInfoService;

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        StudentInfo student = studentInfoService.getStudentByUserId(user.getId());
        model.addAttribute("student", student);
        return "student/profile";
    }

    @PostMapping("/update-profile/{id}")
    public String updateProfile(
            @Validated StudentInfo student,
            BindingResult result,
            RedirectAttributes redirectAttributes
    ) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "数据格式错误！");

            return "redirect:/student/profile";
        }
        System.out.println("yes");
        studentInfoService.updateStudent(student);
        System.out.println("123");
        redirectAttributes.addFlashAttribute("success", "信息更新成功！");
        System.out.println(student.getId());
        return "redirect:/student/profile";
    }
}