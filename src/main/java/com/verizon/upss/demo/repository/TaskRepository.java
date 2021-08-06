package com.verizon.upss.demo.repository;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.verizon.upss.demo.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query(nativeQuery = true, value = "SELECT * FROM  task  WHERE due_date >=:fromDate And due_date <=:toDate")
	Set<Task> getAllactiveTasksByDuration(@Param("fromDate") LocalDateTime fromDate,
			@Param("toDate") LocalDateTime toDate);

}
