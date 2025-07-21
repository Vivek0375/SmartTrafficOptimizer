package com.signal.smarttrafficsignal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SmartTrafficSignalApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartTrafficSignalApplication.class, args);
    }

}
