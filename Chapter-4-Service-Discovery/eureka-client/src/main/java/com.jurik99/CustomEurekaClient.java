package com.jurik99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CustomEurekaClient {

    public static void main(final String[] args) {
        SpringApplication.run(CustomEurekaClient.class);
    }
}
