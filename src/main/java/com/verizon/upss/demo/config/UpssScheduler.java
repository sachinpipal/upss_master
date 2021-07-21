package com.verizon.upss.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.verizon.upss.demo.security.services.EmailService;

@Configuration
@EnableScheduling
public class UpssScheduler {

	@Autowired
	private EmailService emailService;

	@Scheduled(fixedRate = 86400000)
	public void scheduleMail() {
		emailService.scheduleMail();

	}
}