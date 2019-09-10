package com.westernacher.internal.skillprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		@PropertySource("classpath:/spring-boot.properties")
})
public class SkillProfileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkillProfileApplication.class, args);
	}

}



