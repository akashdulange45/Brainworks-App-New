package in.ad.main.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ad.main.entity.Video;
import in.ad.main.repository.VideoRepository;

@Service
public class VideoService {

	 @Autowired
	    private VideoRepository repository;

	    public void saveVideo(Video video){
	        repository.save(video);
	    }

	    public List<Video> getAllVideos(){
	        return repository.findAll();
	    }

	    public Video getVideoById(Long id){
	        return repository.findById(id).orElse(null);
	    }

	    public void deleteVideo(Long id){
	        repository.deleteById(id);
	    }

	    public List<Video> getVideosByCourse(Long courseId){
	        return repository.findByCourseId(courseId);
	    }
}
