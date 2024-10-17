package com.mogumogu.moru.pet.dto;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.pet.entity.PetInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetInfoDTO {
    private Integer piNum;
    private UserInfoDTO userInfoDTO;
    private PetBreedDTO petBreedDTO;
    private String piName;
    private Integer piAdult;
    private Integer piNeutering;
    public static PetInfoDTO toDTO(PetInfo petInfo){
        return  PetInfoDTO.builder()
                .piNum(petInfo.getPiNum())
                .userInfoDTO(UserInfoDTO.toDTO(petInfo.getUserInfo()))
                .petBreedDTO(PetBreedDTO.toDTO(petInfo.getPetBreed()))
                .piName(petInfo.getPiName())
                .piAdult(petInfo.getPiAdult())
                .piNeutering(petInfo.getPiNeutering())
                .build();
    }
}
