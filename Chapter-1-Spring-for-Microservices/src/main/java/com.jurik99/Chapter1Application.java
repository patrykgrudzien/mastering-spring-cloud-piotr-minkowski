package com.jurik99;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Config.class)
public class Chapter1Application {

	public static void main(final String[] args) {
		SpringApplication.run(Chapter1Application.class);
	}
}
