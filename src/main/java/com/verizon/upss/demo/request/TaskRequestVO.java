package com.verizon.upss.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestVO {
	private String name;
	private String dueDate;
	private String priority;
	private Integer userId;
}
