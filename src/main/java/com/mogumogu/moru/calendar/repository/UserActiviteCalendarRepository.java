package com.mogumogu.moru.calendar.repository;

import com.mogumogu.moru.calendar.entity.UserActiviteCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserActiviteCalendarRepository extends JpaRepository<UserActiviteCalendar, Integer> {
    List<UserActiviteCalendar> findByUiIdAndUacDate(String uiId, Date uacDate);
}
