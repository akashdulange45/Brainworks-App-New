package in.ad.main.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import in.ad.main.entity.Student;
import in.ad.main.repository.StudentRepository;
import in.ad.main.services.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/students")
    public String getAllStudents(Model model) {

        model.addAttribute("students",
                studentService.getAllStudents());

        return "students";
    }

 

    @GetMapping("/student/search/email")
    public String searchByEmail(@RequestParam("email") String email, Model model) {

        List<Student> students = new ArrayList<>();

        studentRepository.findByEmail(email).ifPresent(students::add);

        model.addAttribute("students", students);

        return "students";
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return "redirect:/admin/students";
    }
}
