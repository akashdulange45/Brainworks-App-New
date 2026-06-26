package in.ad.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ad.main.entity.Course;
import in.ad.main.services.CourseService;

@Controller
@RequestMapping("/admin")
public class CourseController {

	  @Autowired
	    private CourseService service;

	    @GetMapping("/courses")
	    public String getAllCourses(
	            Model model) {

	        model.addAttribute(
	                "courses",
	                service.getAllCourses());

	        return "courses";
	    }

	    @GetMapping("/course/add")
	    public String addCoursePage(
	            Model model) {

	        model.addAttribute(
	                "course",
	                new Course());

	        return "add-course";
	    }

	    @PostMapping("/course/save")
	    public String saveCourse(
	            @ModelAttribute Course course) {

	        service.saveCourse(course);

	        return "redirect:/admin/courses";
	    }

	    @GetMapping("/course/edit/{id}")
	    public String editCourse(
	            @PathVariable Long id,
	            Model model) {

	        model.addAttribute(
	                "course",
	                service.getCourseById(id));

	        return "add-course";
	    }

	    @GetMapping("/course/delete/{id}")
	    public String deleteCourse(
	            @PathVariable Long id) {

	        service.deleteCourse(id);

	        return "redirect:/admin/courses";
	    }

	    @GetMapping("/course/search")
	    public String searchCourse(
	            @RequestParam String title,
	            Model model) {

	        model.addAttribute(
	                "courses",
	                service.searchCourse(title));

	        return "courses";
	    }
	
	
}
