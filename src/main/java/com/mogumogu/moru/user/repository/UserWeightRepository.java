package com.mogumogu.moru.user.repository;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.dto.UserWeightDto;
import com.mogumogu.moru.user.entity.UserWeightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserWeightRepository extends JpaRepository<UserWeightEntity, String> {

    @Query("SELECT a FROM UserWeightEntity a WHERE a.uiId = :uiId AND a.uwDate >= :startDate AND a.uwDate < :endDate ORDER BY a.uwDate")
    List<UserWeightDto> listUserWeightAndWeek(
            @Param("uiId") String uiId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);

    @Modifying
    @Query("DELETE FROM UserWeightEntity u WHERE u.userInfo.uiId = :uiId AND u.uiId = :uwNum")
    List<UserWeightDto> removeUserWeight(@Param("uiId") String uiId, @Param("uwNum") int uwNum);
}
