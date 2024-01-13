package com.example.opentelemtrydemo;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.opentelemetry.api.GlobalOpenTelemetry;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class OpentelemtrydemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OpentelemtrydemoApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


}
