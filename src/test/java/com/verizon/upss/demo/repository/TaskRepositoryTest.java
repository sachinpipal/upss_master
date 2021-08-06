package com.verizon.upss.demo.repository;

import java.time.LocalDateTime;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.verizon.upss.demo.model.Task;
import com.verizon.upss.demo.model.User;

@DataJpaTest
class TaskRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private TaskRepository taskRepository;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	final void testGetAllactiveTasksByDurationSuccess() {
		User user = new User();
		user.setEmail("sachin@gmail.com");
		user.setFirstName("sachin");
		user.setLastName("pipal");
		User savedUser = testEntityManager.persist(user);
		Task task = new Task();
		LocalDateTime currentDateTime = LocalDateTime.now();
		task.setDueDate(currentDateTime.plusHours(24));
		task.setName("email");
		task.setPriority("High");
		task.setUser(savedUser);
		Task savedTask = testEntityManager.persist(task);
		LocalDateTime fromDate = currentDateTime;
		LocalDateTime toDate = fromDate.plusHours(24);
		Set<Task> tasks = taskRepository.getAllactiveTasksByDuration(fromDate, toDate);
		Assertions.assertEquals(savedTask.getId(), tasks.iterator().next().getId());
		Assertions.assertEquals(task, tasks.iterator().next());
	}

}
