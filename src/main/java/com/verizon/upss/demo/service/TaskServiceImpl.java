package com.verizon.upss.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.verizon.upss.demo.model.Task;
import com.verizon.upss.demo.repository.TaskRepository;
import com.verizon.upss.demo.repository.UserRepository;
import com.verizon.upss.demo.requestVO.TaskRequestVO;
import com.verizon.upss.demo.responseVO.TaskResponseVO;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public TaskResponseVO createTask(TaskRequestVO taskRequestVO) {
		Task task = new Task();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		task.setDueDate(LocalDateTime.parse(taskRequestVO.getDueDate(), dateTimeFormatter));
		task.setName(taskRequestVO.getName());
		task.setPriority(taskRequestVO.getPriority());
		task.setUser(userRepository.getById(taskRequestVO.getUserId()));
		Task savedTask = taskRepository.save(task);
		TaskResponseVO taskResponseVO = populateResponseVO(savedTask);
		return taskResponseVO;
	}

	@Override
	public TaskResponseVO updateTask(Integer taskId, TaskRequestVO taskRequestVO) {
		Task savedTask = taskRepository.getById(taskId);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		savedTask.setDueDate(LocalDateTime.parse(taskRequestVO.getDueDate(), dateTimeFormatter));
		savedTask.setPriority(taskRequestVO.getPriority());
		savedTask.setName(taskRequestVO.getName());
		taskRepository.save(savedTask);
		TaskResponseVO taskResponseVO = populateResponseVO(savedTask);
		return taskResponseVO;
	}

	private TaskResponseVO populateResponseVO(Task savedTask) {
		TaskResponseVO taskResponseVO = new TaskResponseVO();
		taskResponseVO.setTaskId(savedTask.getId());
		taskResponseVO.setDueDate(savedTask.getDueDate().toString());
		taskResponseVO.setName(savedTask.getName());
		taskResponseVO.setPriority(savedTask.getPriority());
		return taskResponseVO;
	}

	@Override
	public boolean deleteTask(Integer taskId) {
		boolean taskDeleted = false;
		if (taskRepository.existsById(taskId)) {
			taskRepository.deleteById(taskId);
			taskDeleted = true;
		}
		return taskDeleted;
	}

}
