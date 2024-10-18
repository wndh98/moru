package com.mogumogu.moru.pet.entity;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.pet.dto.PetBreedDTO;
import com.mogumogu.moru.pet.dto.PetInfoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PET_INFO_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer piNum;
    @ManyToOne
    @JoinColumn(name = "UI_ID", nullable = true)
    private UserInfo userInfo;
    @ManyToOne
    @JoinColumn(name = "PB_NUM", nullable = true)
    private PetBreed petBreed;
    private String piName;
    @Builder.Default
    private byte piAdult = 0;
    @Builder.Default
    private byte piNeutering = 0;

    public static PetInfo toEntity(PetInfoDTO petInfoDTO) {
        return PetInfo.builder()
                .piNum(petInfoDTO.getPiNum())
                .userInfo(UserInfo.toEntity(petInfoDTO.getUserInfoDTO()))
                .petBreed(PetBreed.toEntity(petInfoDTO.getPetBreedDTO()))
                .piName(petInfoDTO.getPiName())
                .piAdult(petInfoDTO.getPiAdult())
                .piNeutering(petInfoDTO.getPiNeutering())
                .build();
    }

    @PrePersist
    public void prePersist() {
//        if (piAdult == 0) {
//            this.piAdult = 0;
//        }
//        if (piNeutering == 0) {
//            this.piNeutering = 0;
//        }
    }
}
