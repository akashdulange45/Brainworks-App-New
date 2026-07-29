package in.ad.main.controller;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import in.ad.main.util.FileUploadUtil;

@Controller
public class VideoStreamController {

	@Autowired
	private FileUploadUtil fileUploadUtil;

	@GetMapping("/videos/{fileName:.+}")
	@ResponseBody
	public ResponseEntity<Resource> streamVideo(@PathVariable String fileName) throws IOException {

		Path path = fileUploadUtil.getVideoPath(fileName);

		Resource resource = new UrlResource(path.toUri());

		if (!resource.exists()) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileName + "\"")
				.contentType(MediaType.parseMediaType("video/mp4")).body(resource);
	}
}
