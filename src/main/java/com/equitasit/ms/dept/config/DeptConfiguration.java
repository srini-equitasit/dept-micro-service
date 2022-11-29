package com.equitasit.ms.dept.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeptConfiguration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
