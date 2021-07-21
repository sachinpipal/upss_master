package com.verizon.upss.demo.responseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponseVO {

	private String name;
	private String dueDate;
	private String priority;
	private Integer taskId;

}
