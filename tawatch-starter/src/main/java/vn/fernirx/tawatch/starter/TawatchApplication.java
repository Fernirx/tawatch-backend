package vn.fernirx.tawatch.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages = {"vn.fernirx.tawatch"})
@EnableJpaRepositories(basePackages = "vn.fernirx.tawatch")
@EntityScan(basePackages = "vn.fernirx.tawatch")
@ConfigurationPropertiesScan("vn.fernirx.tawatch")
@EnableAsync
public class TawatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TawatchApplication.class, args);
    }

}
