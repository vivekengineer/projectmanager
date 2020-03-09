package com.fsd.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude= {SecurityAutoConfiguration.class} )
public class ProjectmangerApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectmangerApplication.class, args);
	}
}
