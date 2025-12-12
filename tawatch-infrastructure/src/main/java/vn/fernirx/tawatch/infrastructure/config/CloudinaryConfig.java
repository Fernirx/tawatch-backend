package vn.fernirx.tawatch.infrastructure.config;

import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.fernirx.tawatch.infrastructure.properties.CloudinaryProperties;
import vn.fernirx.tawatch.infrastructure.storage.CloudinaryService;

@Configuration
@RequiredArgsConstructor
public class CloudinaryConfig {

    private final CloudinaryProperties cloudinaryProperties;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(cloudinaryProperties.getCloudinaryUrl());
    }

    @Bean
    public CloudinaryService cloudinaryService(Cloudinary cloudinary) {
        return new CloudinaryService(cloudinary, cloudinaryProperties);
    }
}
