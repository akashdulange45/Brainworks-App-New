package in.ad.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Course;
import in.ad.main.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repository;

	public List<Course> getAllCourses() {
		return repository.findAll();
	}

	public Course getCourseById(Long id) {

		return repository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
	}

	public List<Course> searchCourse(String title) {

		return repository.findByTitleContainingIgnoreCase(title);
	}

	public Course saveCourse(Course course) {

		return repository.save(course);
	}

	public void deleteCourse(Long id) {

		repository.deleteById(id);
	}

	public long totalCourses() {

		return repository.count();
	}

}
