package com.jurik99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class CustomEurekaServer {

    public static void main(final String[] args) {
        SpringApplication.run(CustomEurekaServer.class);
    }
}
