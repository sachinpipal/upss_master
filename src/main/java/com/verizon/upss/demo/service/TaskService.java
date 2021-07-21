package com.verizon.upss.demo.service;

import com.verizon.upss.demo.requestVO.TaskRequestVO;
import com.verizon.upss.demo.responseVO.TaskResponseVO;

public interface TaskService {

	public TaskResponseVO createTask(TaskRequestVO taskRequestVO);

	public TaskResponseVO updateTask(Integer taskId, TaskRequestVO taskRequestVO);

	public boolean deleteTask(Integer taskId);

}
