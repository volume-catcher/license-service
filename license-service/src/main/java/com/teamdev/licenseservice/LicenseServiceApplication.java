package com.teamdev.licenseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class LicenseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicenseServiceApplication.class, args);
	}

}
