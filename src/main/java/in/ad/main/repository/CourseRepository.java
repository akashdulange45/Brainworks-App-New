package in.ad.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ad.main.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
List<Course> findByTitleContainingIgnoreCase(String title);
}
