package com.mogumogu.moru.calendar.repository;

import com.mogumogu.moru.calendar.entity.UserActiviteCalendar;
import com.mogumogu.moru.calendar.entity.UserFoodCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserFoodCalendarRepository extends JpaRepository<UserFoodCalendar, Integer> {
    List<UserFoodCalendar> findByUiIdUfcDate(String uiId, Date ufcDate);
}
