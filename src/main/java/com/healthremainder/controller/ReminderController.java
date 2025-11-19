package com.healthremainder.controller;

import com.healthremainder.entity.Reminder;
import com.healthremainder.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reminders")
@CrossOrigin(origins = "*")
public class ReminderController {

    @Autowired
    private ReminderService reminderService;

    // Create a new reminder
    @PostMapping
    public ResponseEntity<Reminder> createReminder(@RequestBody Reminder reminder) {
        Reminder savedReminder = reminderService.createReminder(reminder);
        return ResponseEntity.ok(savedReminder);
    }

    // Get all reminders for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Reminder>> getRemindersByUser(@PathVariable Long userId) {
        List<Reminder> reminders = reminderService.getRemindersByUser(userId);
        return ResponseEntity.ok(reminders);
    }

    // Get reminders for a user by date
    @GetMapping("/user/{userId}/date/{date}")
    public ResponseEntity<List<Reminder>> getRemindersByDate(
            @PathVariable Long userId,
            @PathVariable String date) {

        LocalDate parsedDate = LocalDate.parse(date);
        List<Reminder> reminders = reminderService.getRemindersByUserAndDate(userId, parsedDate);
        return ResponseEntity.ok(reminders);
    }

    // Get reminders for a user by category
    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<List<Reminder>> getRemindersByCategory(
            @PathVariable Long userId,
            @PathVariable String category) {

        List<Reminder> reminders = reminderService.getRemindersByCategory(userId, category);
        return ResponseEntity.ok(reminders);
    }

    // Update a reminder by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reminder> updateReminder(
            @PathVariable Long id,
            @RequestBody Reminder reminder) {

        Reminder updatedReminder = reminderService.updateReminder(id, reminder);
        return ResponseEntity.ok(updatedReminder);
    }

    // Delete a reminder by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.ok("Reminder deleted successfully!");
    }

    // Update status (PENDING/APPROVED/REJECTED) of a reminder
    @PatchMapping("/{id}/status/{status}")
    public ResponseEntity<Reminder> updateStatus(
            @PathVariable Long id,
            @PathVariable Reminder.Status status) {

        Reminder updatedReminder = reminderService.updateStatus(id, status);
        return ResponseEntity.ok(updatedReminder);
    }

    // Get reminder by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reminder> getReminderById(@PathVariable Long id) {
        Reminder reminder = reminderService.getReminderById(id);
        return ResponseEntity.ok(reminder);
    }
}
