package vn.fernirx.tawatch.infrastructure.properties;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties(prefix = "application.storage.cloudinary")
public class CloudinaryProperties {

    /** Cloudinary cloud name (unique identifier for your Cloudinary account) */
    @NotBlank(message = "Cloudinary cloud name must not be blank")
    private String cloudName;

    /** Cloudinary API Key for authentication */
    @NotBlank(message = "Cloudinary API key must not be blank")
    private String apiKey;

    /** Cloudinary API Secret for secure authentication */
    @NotBlank(message = "Cloudinary API secret must not be blank")
    private String apiSecret;

    /** Folder paths for different types of files in Cloudinary */
    private Folder folder = new Folder();

    @Data
    public static class Folder {
        /** Folder path for user profile avatars */
        private String avatars = "tawatch/avatars";

        /** Folder path for product images */
        private String products = "tawatch/products";

        /** Folder path for brand images */
        private String brand = "tawatch/brand";

        /** Folder path for category images */
        private String categories = "tawatch/categories";
    }

    public String getCloudinaryUrl() {
        return String.format("cloudinary://%s:%s@%s",
                apiKey, apiSecret, cloudName);
    }
}
