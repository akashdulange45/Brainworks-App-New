package in.ad.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ad.main.entity.Video;
import java.util.List;


@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

	List<Video> findByCourseId(Long courseId);
	
	
}
