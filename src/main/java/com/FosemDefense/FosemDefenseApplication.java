package com.FosemDefense;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FosemDefenseApplication {
	// http://localhost:8080/h2-console h2 콘솔 확인
	// http://localhost:8080/ 사이트 진입점
	public static void main(String[] args) {
		SpringApplication.run(FosemDefenseApplication.class, args);
	}

}
