package com.mogumogu.moru.pet.service;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.pet.dto.PetInfoDTO;
import com.mogumogu.moru.pet.entity.PetInfo;
import com.mogumogu.moru.pet.exception.PetNotFoundException;
import com.mogumogu.moru.pet.repository.PetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetInfoServiceImpl implements PetInfoService {
    @Autowired
    private PetInfoRepository petInfoRepository;

    @Override
    public int petInfoAdd(PetInfoDTO petInfoDTO) {
        // TODO : jwt 완성후 수정
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        PetInfo petInfo = PetInfo.toEntity(petInfoDTO);
        petInfo.setUserInfo(userInfo);
        petInfoRepository.save(petInfo);
        return 1;
    }

    @Override
    public int petInfoRemove(Integer[] piNum) throws PetNotFoundException {

        for (Integer num : piNum) {
            PetInfo petInfo = petInfoRepository.findById(num).orElseThrow(PetNotFoundException::new);
            // TODO : JWT 정보와 petInfo id 동일한지 확인
            petInfoRepository.deleteById(num);
        }
        return 1;
    }
}
