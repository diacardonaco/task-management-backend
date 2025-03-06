package com.example.seek;

import com.example.seek.dto.TaskDTO;
import com.example.seek.entity.Task;
import com.example.seek.entity.TaskStatus;
import com.example.seek.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SeekApplicationTests {

	@Autowired
	private TaskService taskService;

	@Test
	void testCreateTask() {
		TaskDTO taskDTO = new TaskDTO("Test Task", "Test Description", TaskStatus.TODO);
		assertNotNull(taskService.createTask(taskDTO));
	}

	@Test
	void testGetAllTasks() {
		assertNotNull(taskService.getAllTasks());
	}

	@Test
	void testUpdateTask() {
		TaskDTO taskDTO = new TaskDTO("Updated Task", "Updated Description", TaskStatus.IN_PROGRESS);
		Task createdTask = taskService.createTask(new TaskDTO("Test Task", "Test Description", TaskStatus.TODO));
		assertNotNull(taskService.updateTask(createdTask.getId(), taskDTO));
	}

	@Test
	void testDeleteTask() {
		Task createdTask = taskService.createTask(new TaskDTO("Task to Delete", "Description", TaskStatus.TODO));
		assertDoesNotThrow(() -> taskService.deleteTask(createdTask.getId()));
	}

	@Test
	void contextLoads() {
	}
}
