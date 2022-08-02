package com.project.skb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SkbApplication {

	public static void main(String[] args) {
		SpringApplication.run(SkbApplication.class, args);
	}

}
