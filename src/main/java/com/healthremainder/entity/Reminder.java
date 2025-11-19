package com.healthremainder.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reminders")
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "task_name", nullable = false)
	private String taskName;

	@Column(nullable = false)
	private LocalTime time;

	@Column(nullable = false)
	private String category;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status; // Changed from Boolean to Enum

	@Column(nullable = false)
	private LocalDate date;

	// Enum for status
	public enum Status {
		PENDING, APPROVED, REJECTED
	}

	public Reminder() {
	}

	public Reminder(Long id, User user, String taskName, LocalTime time, String category, Status status,
			LocalDate date) {
		this.id = id;
		this.user = user;
		this.taskName = taskName;
		this.time = time;
		this.category = category;
		this.status = status;
		this.date = date;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", user=" + user + ", taskName=" + taskName + ", time=" + time + ", category="
				+ category + ", status=" + status + ", date=" + date + "]";
	}
}
