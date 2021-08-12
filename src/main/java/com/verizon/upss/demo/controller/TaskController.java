package com.verizon.upss.demo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.upss.demo.commons.util.MessageResponse;
import com.verizon.upss.demo.request.TaskRequestVO;
import com.verizon.upss.demo.response.TaskResponseVO;
import com.verizon.upss.demo.security.services.UserDetailsImpl;
import com.verizon.upss.demo.service.TaskService;

@RestController
@RequestMapping("/v1/task")
public class TaskController {
	@Autowired
	private TaskService taskService;

	@PostMapping("/create")
	public ResponseEntity<MessageResponse> createTask(@RequestBody TaskRequestVO taskRequestVO, Principal principal) {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) principal;
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) token.getPrincipal();
		taskRequestVO.setUserId(userDetailsImpl.getId());
		TaskResponseVO taskResponseVO = taskService.createTask(taskRequestVO);
		return ResponseEntity.ok(new MessageResponse("Task created successfully !!", taskResponseVO));
	}

	@PatchMapping("/{taskId}")
	public ResponseEntity<MessageResponse> updateTask(@PathVariable Integer taskId,
			@RequestBody TaskRequestVO taskRequestVO) {
		TaskResponseVO taskResponseVO = taskService.updateTask(taskId, taskRequestVO);
		return ResponseEntity.ok(new MessageResponse("Task updated successfully !!", taskResponseVO));

	}

	@DeleteMapping("/{taskId}")
	public ResponseEntity<MessageResponse> deleteTask(@PathVariable Integer taskId) {
		boolean taskDelted = taskService.deleteTask(taskId);
		String responseMessage = taskDelted ? "Task deleted successfully !!" : "Invalid Task";
		return ResponseEntity.ok(new MessageResponse(responseMessage, ""));
	}

}
