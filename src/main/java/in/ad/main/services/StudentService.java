package in.ad.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Student;
import in.ad.main.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository repository;

	public Student getStudentById(Long id) {

	    return repository.findById(id)
	            .orElseThrow(() ->
	                    new RuntimeException("Student Not Found"));
	}

	public Student getStudentByEmail(String email) {

	    return repository.findByEmail(email)
	            .orElseThrow(() ->
	                    new RuntimeException("Student Not Found"));
	}

	public void deleteStudent(Long id) {

	    repository.deleteById(id);
	}

	public List<Student> getAllStudents(){
		return repository.findAll();
	}
	
}
