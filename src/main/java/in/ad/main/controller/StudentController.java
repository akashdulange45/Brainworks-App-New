package in.ad.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ad.main.services.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model) {

        model.addAttribute("students",
                studentService.getAllStudents());

        return "students";
    }

    @GetMapping("/student/search/id")
    public String searchById(
            @RequestParam Long id,
            Model model) {

        model.addAttribute("student",
                studentService.getStudentById(id));

        return "student-details";
    }

    @GetMapping("/student/search/email")
    public String searchByEmail(
            @RequestParam String email,
            Model model) {

        model.addAttribute("student",
                studentService.getStudentByEmail(email));

        return "student-details";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/admin/students";
    }
}
