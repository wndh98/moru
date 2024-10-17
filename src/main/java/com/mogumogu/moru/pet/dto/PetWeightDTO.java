package com.mogumogu.moru.pet.dto;

import com.mogumogu.moru.pet.entity.PetWeight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetWeightDTO {
    private Integer pwNum;
    private PetInfoDTO petInfoDTO;
    private Integer pwWeight;
    private LocalDateTime pwDate;
    public static PetWeightDTO toDTO(PetWeight petWeight){
        return PetWeightDTO.builder()
                .pwNum(petWeight.getPwNum())
                .petInfoDTO(PetInfoDTO.toDTO(petWeight.getPetInfo()))
                .pwWeight(petWeight.getPwWeight())
                .pwDate(petWeight.getPwDate())
                .build();
    }
}
