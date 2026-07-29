package in.ad.main.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Student;
import in.ad.main.exception.ResourceNotFoundException;
import in.ad.main.repository.StudentRepository;

@Service
public class StudentService {
	
	private static final Logger loginLogger = LoggerFactory.getLogger(StudentService.class);
	
	
	@Autowired
	private StudentRepository repository;

	public Student getStudentById(Long id) {

	    return repository.findById(id)
	            .orElseThrow(() ->
	                    new ResourceNotFoundException("Student Not Found"));
	}

	public Student getStudentByEmail(String email) {
		
		loginLogger.info("Login attempt for email : {}",email);
		
		Student student = repository.findByEmail(email)
				.orElseThrow(() -> {
	                loginLogger.warn("Login failed. Student not found: {}", email);
	                return new RuntimeException("Student Not Found");
	            });
		
		loginLogger.info("Student found: {}", student.getName());
		
//	    return repository.findByEmail(email)
//	            .orElseThrow(() ->
//	                    new RuntimeException("Student Not Found"));
	
		return student;
	
	}

	public void deleteStudent(Long id) {
		 loginLogger.info("Deleting student with ID: {}", id);
	    repository.deleteById(id);
	    loginLogger.info("Student deleted successfully. ID: {}", id);
	    
	}

	public List<Student> getAllStudents(){
		loginLogger.info("Fetching all students");

	    List<Student> students = repository.findAll();

	    loginLogger.info("Total students found: {}", students.size());

	    return students;
	}
	
}
