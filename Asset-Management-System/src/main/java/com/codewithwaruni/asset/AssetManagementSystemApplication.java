package com.codewithwaruni.asset;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssetManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssetManagementSystemApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}

}
