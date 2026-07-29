package in.ad.main.controller;

import java.io.IOException;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import in.ad.main.entity.Video;
import in.ad.main.services.CourseService;
import in.ad.main.services.VideoService;
import in.ad.main.util.FileUploadUtil;

@Controller
@RequestMapping("/admin")
public class VideoController {
	@Autowired
	private VideoService videoService;

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private FileUploadUtil fileUploadUtil;

	// Upload Page
	@GetMapping("/upload-video")
	public String uploadPage(Model model) {

		model.addAttribute("video", new Video());
		model.addAttribute("courses", courseService.getAllCourses());

		return "upload-video";
	}

	
	//Save video
	@PostMapping("/save-video")
	public String saveVideo(@ModelAttribute Video video,
	                        @RequestParam("videoFile") MultipartFile videoFile)
	        throws IOException {

	    // Upload video using FileUploadUtil
	    String fileName = fileUploadUtil.uploadVideo(videoFile);

	    // Save file name in database
	    video.setVideoPath(fileName);

	    // Save video details
	    videoService.saveVideo(video);

	    return "redirect:/admin/videos";
	}
	
	//see All videos
	@GetMapping("/videos")
	public String allVideos(Model model){

	    model.addAttribute(
	            "videos",
	            videoService.getAllVideos());

	    return "videos";
	}
	
	
	//edit video
	@GetMapping("/edit-video/{id}")
	public String editVideo(
	        @PathVariable Long id,
	        Model model){

	    model.addAttribute(
	            "video",
	            videoService.getVideoById(id));

	    model.addAttribute(
	            "courses",
	            courseService.getAllCourses());

	    return "edit-video";
	}
	
	//update 
	@PostMapping("/update-video")
	public String updateVideo(
	        @ModelAttribute Video video){

	    videoService.saveVideo(video);

	    return "redirect:/admin/videos";
	}
	
	@GetMapping("/delete-video/{id}")
	public String deleteVideo(@PathVariable Long id) throws IOException {

	    Video video = videoService.getVideoById(id);

	    if (video != null) {
	        fileUploadUtil.deleteVideo(video.getVideoPath());
	        videoService.deleteVideo(id);
	    }

	    return "redirect:/admin/videos";
	}
	

}
