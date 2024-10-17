package com.mogumogu.moru.pet.dto;

import com.mogumogu.moru.pet.entity.PetBreed;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetBreedDTO {
    private Integer pbNum;
    private String pbName;
    private Integer pbWeightMin;
    private Integer pbWeightMax;

    public static PetBreedDTO toDTO(PetBreed petBreed) {
        return PetBreedDTO.builder()
                .pbNum(petBreed.getPbNum())
                .pbName(petBreed.getPbName())
                .pbWeightMin(petBreed.getPbWeightMin())
                .pbWeightMax(petBreed.getPbWeightMax())
                .build();
    }
}
