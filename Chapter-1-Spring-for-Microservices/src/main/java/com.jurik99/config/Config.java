package com.jurik99.config;

import lombok.Getter;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "my")
public class Config {

	@Getter
	private List<String> servers = new ArrayList<>();
}
