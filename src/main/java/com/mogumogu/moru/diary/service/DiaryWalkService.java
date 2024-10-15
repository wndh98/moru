package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DiaryWalkService {
    int diaryWalkAdd(DiaryWalkDTO diaryWalkDTO);

    int diaryRemove(Integer dwNum) throws DiaryWalkNotFoundException;

    int diaryModify(DiaryWalkDTO diaryWalkDTO) throws DiaryWalkNotFoundException;

    List<DiaryWalkDTO> diaryWalkList(Pageable pageable, String uiId);

    DiaryWalkDTO diaryWalkDetails(Integer dwNum) throws DiaryWalkNotFoundException;
}
