package com.healthremainder.service;

import com.healthremainder.entity.Reminder;
import com.healthremainder.entity.Reminder.Status;

import java.time.LocalDate;
import java.util.List;

public interface ReminderService {

	Reminder createReminder(Reminder reminder);

	List<Reminder> getRemindersByUser(Long userId);

	List<Reminder> getRemindersByUserAndDate(Long userId, LocalDate date);

	List<Reminder> getRemindersByCategory(Long userId, String category);

	Reminder updateReminder(Long id, Reminder reminder);

	void deleteReminder(Long id);

	Reminder getReminderById(Long id);

	Reminder updateStatus(Long id, Status status);

}
