package com.example.opentelemtrydemo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OpentelemtrydemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpentelemtrydemoApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}
