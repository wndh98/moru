package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.board.dto.UserInfoDTO;
import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.repository.DiaryWalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryWalkServiceImpl implements DiaryWalkService{
    @Autowired
    private DiaryWalkRepository diaryWalkRepository;

    @Override
    public int diaryWalkAdd(DiaryWalkDTO diaryWalkDTO) {
        int result = 1;
        System.out.println(diaryWalkDTO);
        DiaryWalk diaryWalk = diaryWalkRepository.save(DiaryWalk.toEntity(diaryWalkDTO));
        if(diaryWalk.getDwNum()==null){
            result = 0;
        }
        return result;
    }
}
