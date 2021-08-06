package com.verizon.upss.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpssApplication {

	private static final Logger logger = LoggerFactory.getLogger(UpssApplication.class);

	public static void main(String[] args) {
		logger.info("application started");
		logger.debug("application started");
		SpringApplication.run(UpssApplication.class, args);
		logger.debug("done sachin");
	}

}
