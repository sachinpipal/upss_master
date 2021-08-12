package com.verizon.upss.demo.security.services;

import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.verizon.upss.demo.exception.UpssServiceExcpetion;
import com.verizon.upss.demo.model.Task;
import com.verizon.upss.demo.model.User;
import com.verizon.upss.demo.repository.TaskRepository;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Value("${active.task.duration}")
	private Integer duration;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void scheduleMail() {
		Session session = createMailSession();
		LocalDateTime fromDate = LocalDateTime.now();
		LocalDateTime toDate = fromDate.plusHours(duration);
		Set<Task> tasks = taskRepository.getAllactiveTasksByDuration(fromDate, toDate);
		if (!CollectionUtils.isEmpty(tasks)) {
			for (Task task : tasks) {
				createEmail(task, session);
			}

		}

	}

	private Session createMailSession() {
		String from = "sachinpipal@gmail.com";
		String password = "######";
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.ssl.checkserveridentity", true);
		props.put("mail.smtp.socketFactory.fallback", "false");
		return Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
	}

	private void createEmail(Task task, Session session) {
		// String to = "sachinpipal23@gmail.com";
		String from = "sachinpipal@gmail.com";
		User user = task.getUser();
		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject("Nothing Special.." + task.getName());
			message.setText("Send Mail By Java Programmm...." + task.getName() + task.getPriority());
			Transport.send(message);
			logger.info("message sent successfully");
		} catch (MessagingException ex) {
			throw new UpssServiceExcpetion(ex, "Error :{} Exception occured while sending email for userId {} ",
					ex.getMessage(), user.getId(), 303);
		}
	}

}
