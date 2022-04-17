package com.otp.otpserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.TaskScheduler;

@SpringBootApplication
public class OtpServerApplication {

    @Autowired
    private static TaskScheduler taskScheduler;

    public static void main(String[] args) {
        SpringApplication.run(OtpServerApplication.class, args);
    }

}
