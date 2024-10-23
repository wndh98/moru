package com.mogumogu.moru.template.dto;

import com.mogumogu.moru.template.entity.UserActivite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserActiviteDTO {
    private Integer uaNum;
    private String uaName;
    private Integer uaIntensity;
    private String uaCategory;

    public static UserActiviteDTO toDTO(UserActivite userActivite){
        return UserActiviteDTO.builder()
                .uaNum(userActivite.getUaNum())
                .uaName(userActivite.getUaName())
                .uaIntensity(userActivite.getUaIntensity())
                .uaCategory(userActivite.getUaCategory())
                .build();
    }
}
