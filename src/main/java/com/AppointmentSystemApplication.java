package com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@SpringBootApplication(scanBasePackages = "com")
@RestController
class AppointmentSystemApplication{
    public static void main(String[] args) {
        SpringApplication.run(AppointmentSystemApplication.class, args);
    }
}

