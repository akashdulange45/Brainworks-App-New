package in.ad.main.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import in.ad.main.exception.FileStorageException;

@Component
public class FileUploadUtil {

    @Value("${video.upload.path:D:/SpringBoot/Microservices/BrainworksData/videos}")
    private String uploadDir;

    /**
     * Upload Video
     */
    public String uploadVideo(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new FileStorageException("Please select a video file.");
        }

        // Validate Content Type
        String contentType = file.getContentType();

        if (contentType == null || !contentType.startsWith("video/")) {
            throw new FileStorageException("Only video files are allowed.");
        }

        // Create Upload Folder if not exists
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate Unique File Name
        String originalFileName = file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null && originalFileName.contains(".")) {
            extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }

        String fileName = UUID.randomUUID().toString() + extension;

        // Save File
        Path filePath = uploadPath.resolve(fileName);

        try {

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {

            throw new FileStorageException(
                    "Video upload failed.", e);
        }

        return fileName;
    }

    /**
     * Get Video Path
     */
    public Path getVideoPath(String fileName) {

        return Paths.get(uploadDir).resolve(fileName);
    }

    /**
     * Delete Video
     */
    public boolean deleteVideo(String fileName) throws IOException {

        Path filePath = getVideoPath(fileName);

        return Files.deleteIfExists(filePath);
    }

    /**
     * Check Video Exists
     */
    public boolean videoExists(String fileName) {

        return Files.exists(getVideoPath(fileName));
    }

    /**
     * Get Upload Directory
     */
    public String getUploadDirectory() {
        return uploadDir;
    }

}