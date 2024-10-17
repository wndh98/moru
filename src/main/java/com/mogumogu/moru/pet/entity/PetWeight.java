package com.mogumogu.moru.pet.entity;

import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.dto.PetWeightDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "PET_WEIGHT_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetWeight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pwNum;
    @ManyToOne
    @JoinColumn(name = "PI_NUM", nullable = false)
    private PetInfo petInfo;
    private Integer pwWeight;
    private LocalDateTime pwDate;

    public static PetWeight toEntity(PetWeightDTO petWeightDTO) {
        return PetWeight.builder()
                .pwNum(petWeightDTO.getPwNum())
                .petInfo(PetInfo.toEntity(petWeightDTO.getPetInfoDTO()))
                .pwWeight(petWeightDTO.getPwWeight())
                .pwDate(petWeightDTO.getPwDate())
                .build();
    }
}
