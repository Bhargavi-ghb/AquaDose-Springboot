package com.healthremainder.repository;

import com.healthremainder.entity.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {

    List<Reminder> findByUser_Id(Long userId);

    List<Reminder> findByUser_IdAndDate(Long userId, LocalDate date);

    List<Reminder> findByUser_IdAndCategory(Long userId, String category);
}

