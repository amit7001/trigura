package com.trafigura.equityposition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@EntityScan("com.trafigura.equityposition.entity")
public class EquityPositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(EquityPositionApplication.class, args);
	}

}
