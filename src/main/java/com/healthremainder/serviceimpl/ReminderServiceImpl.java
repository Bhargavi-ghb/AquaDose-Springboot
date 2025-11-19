package com.healthremainder.serviceimpl;

import com.healthremainder.entity.Reminder;
import com.healthremainder.entity.User;
import com.healthremainder.repository.ReminderRepository;
import com.healthremainder.repository.UserRepository;
import com.healthremainder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private ReminderRepository reminderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Reminder createReminder(Reminder reminder) {
        Long userId = reminder.getUser().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        reminder.setUser(user);

        // Set default status to PENDING if not provided
        if (reminder.getStatus() == null) {
            reminder.setStatus(Reminder.Status.PENDING);
        }

        return reminderRepository.save(reminder);
    }

    @Override
    public List<Reminder> getRemindersByUser(Long userId) {
        return reminderRepository.findByUser_Id(userId);
    }

    @Override
    public List<Reminder> getRemindersByUserAndDate(Long userId, LocalDate date) {
        return reminderRepository.findByUser_IdAndDate(userId, date);
    }

    @Override
    public List<Reminder> getRemindersByCategory(Long userId, String category) {
        return reminderRepository.findByUser_IdAndCategory(userId, category);
    }

    @Override
    public Reminder updateReminder(Long id, Reminder reminder) {
        Reminder existing = reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));

        existing.setTaskName(reminder.getTaskName());
        existing.setCategory(reminder.getCategory());
        existing.setDate(reminder.getDate());
        existing.setTime(reminder.getTime());

        if (reminder.getStatus() != null) {
            existing.setStatus(reminder.getStatus());
        }

        return reminderRepository.save(existing);
    }

    @Override
    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }

    @Override
    public Reminder getReminderById(Long id) {
        return reminderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reminder not found"));
    }

    // New method to update status by enum value
    public Reminder updateStatus(Long id, Reminder.Status status) {
        Reminder reminder = getReminderById(id);
        reminder.setStatus(status);
        return reminderRepository.save(reminder);
    }
}
