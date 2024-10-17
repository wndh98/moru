package com.mogumogu.moru.pet.entity;

import com.mogumogu.moru.pet.dto.PetBreedDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "PET_BREED_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetBreed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pbNum;
    private String pbName;
    private Integer pbWeightMin;
    private Integer pbWeightMax;
    public static PetBreed toEntity(PetBreedDTO petBreedDTO){
        return PetBreed.builder()
                .pbNum(petBreedDTO.getPbNum())
                .pbName(petBreedDTO.getPbName())
                .pbWeightMin(petBreedDTO.getPbWeightMin())
                .pbWeightMax(petBreedDTO.getPbWeightMax())
                .build();
    }

}
