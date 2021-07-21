package com.verizon.upss.demo.security.services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.verizon.upss.demo.model.Task;
import com.verizon.upss.demo.model.User;
import com.verizon.upss.demo.repository.TaskRepository;

@Service
@Transactional
public class EmailServiceImpl implements EmailService {

	@Autowired

	@Value("${active.task.duration}")
	private Integer duration;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public void scheduleMail() {
		Session session = createMailSession();
		LocalDateTime fromDate = LocalDateTime.now();
		LocalDateTime toDate = fromDate.plusHours(duration);
		Set<Task> tasks = taskRepository.getAllactiveTasksByDuration(fromDate,toDate);
		if (!CollectionUtils.isEmpty(tasks)) {
			for (Task task : tasks) {
				try {
					createEmail(task, session);
				} catch (MessagingException | IOException e) {
					e.printStackTrace();
				}
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
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		return session;
	}

	private void createEmail(Task task, Session session) throws AddressException, MessagingException, IOException {
		// String to = "sachinpipal23@gmail.com";
		String from = "sachinpipal@gmail.com";
		try {
			User user = task.getUser();
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
			message.setSubject("Nothing Special.." + task.getName());
			message.setText("Send Mail By Java Programmm...." + task.getName() + task.getPriority());
			Transport.send(message);
			System.out.println("message sent successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
