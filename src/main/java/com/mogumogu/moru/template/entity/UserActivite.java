package com.mogumogu.moru.template.entity;

import com.mogumogu.moru.template.dto.UserActiviteDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_ACTIVITE_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserActivite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uaNum;
    private String uaName;
    private Integer uaIntensity;
    private String uaCategory;

    public static UserActivite toEntity(UserActiviteDTO userActiviteDTO){
        return UserActivite.builder()
                .uaNum(userActiviteDTO.getUaNum())
                .uaName(userActiviteDTO.getUaName())
                .uaIntensity(userActiviteDTO.getUaIntensity())
                .uaCategory(userActiviteDTO.getUaCategory())
                .build();
    }
}
