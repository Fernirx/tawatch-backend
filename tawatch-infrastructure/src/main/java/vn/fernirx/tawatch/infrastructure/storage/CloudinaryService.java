package vn.fernirx.tawatch.infrastructure.storage;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vn.fernirx.tawatch.infrastructure.properties.CloudinaryProperties;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CloudinaryService {
    private final Cloudinary cloudinary;
    private final CloudinaryProperties properties;

    public Map<String, Object> uploadImage(MultipartFile file, String folder, String publicId) throws IOException {
        try {
            Map<String, Object> params = ObjectUtils.asMap(
                    "folder", folder,
                    "public_id", publicId,
                    "overwrite", true,
                    "resource_type", "image"
            );

            return cloudinary.uploader().upload(file.getBytes(), params);
        } catch (IOException e) {
            throw new IOException("Failed to upload image to Cloudinary", e);
        }
    }

    public String uploadAvatar(MultipartFile file, Long userId) throws IOException {
        String folder = properties.getFolder().getAvatars();   // "tawatch/avatars"
        String publicId = "user_" + userId;

        Map<String, Object> result = uploadImage(file, folder, publicId);
        return (String) result.get("secure_url");
    }

    public void deleteImage(String folder, String publicId) throws IOException {
        cloudinary.uploader().destroy(folder + "/" + publicId, ObjectUtils.asMap());
    }

    public void deleteImageByUrl(String imageUrl) throws IOException {
        String publicId = extractFullPublicId(imageUrl);
        if (publicId != null) {
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
        }
    }

    private String extractFullPublicId(String url) {
        if (url == null || url.isEmpty()) {
            return null;
        }

        try {
            // Cloudinary URL format: https://res.cloudinary.com/cloudname/image/upload/v1234567890/folder/public_id.jpg
            // Hoặc: https://res.cloudinary.com/cloudname/image/upload/folder/public_id.jpg

            // Tìm phần sau "/upload/"
            String[] parts = url.split("/upload/");
            if (parts.length < 2) {
                return null;
            }

            String path = getPath(parts);

            log.debug("Extracted publicId: {} from URL: {}", path, url);
            return path;

        } catch (Exception e) {
            log.warn("Could not extract publicId from URL: {}", url, e);
            return null;
        }
    }

    private static String getPath(String[] parts) {
        String path = parts[1];

        // Loại bỏ version nếu có (v1234567890/)
        if (path.startsWith("v")) {
            int slashIndex = path.indexOf('/');
            if (slashIndex > 0) {
                path = path.substring(slashIndex + 1);
            }
        }

        // Loại bỏ phần đuôi file (.jpg, .png, .jpeg)
        int dotIndex = path.lastIndexOf('.');
        if (dotIndex > 0) {
            path = path.substring(0, dotIndex);
        }
        return path;
    }
}
