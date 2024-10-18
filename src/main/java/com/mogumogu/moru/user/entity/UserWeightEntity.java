package com.mogumogu.moru.user.entity;

import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.dto.UserWeightDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;



@Entity
@Table(name = "USER_WEIGHT_TB")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWeightEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UW_NUM", nullable = false)
    private Integer uwNum;
    @Column(name = "UI_ID")
    private String uiId;
    @Builder.Default
    private LocalDate uwDate=LocalDate.now();
    private Integer uwWeight;
    private Integer uwBodyFat;
    private Integer uwMuscle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UI_ID",insertable = false, updatable = false)
    private UserInfoEntity userInfo;

    public static UserWeightEntity toEntity(UserWeightDto userWeightDto){
        return UserWeightEntity.builder()
                .uwNum(userWeightDto.getUwNum())
                .uiId(userWeightDto.getUiId())
                .uwDate(userWeightDto.getUwDate())
                .uwWeight(userWeightDto.getUwWeight())
                .uwBodyFat(userWeightDto.getUwWeight())
                .uwMuscle(userWeightDto.getUwWeight())
                .build();
    }

    @PrePersist
    public void prePersist() {
        if (uwDate == null) {
            this.uwDate = LocalDate.now();
        }
    }
}
