package com.br.schneiderstream.schneiderstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SchneiderstreamApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchneiderstreamApplication.class, args);
	}

}
