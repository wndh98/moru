package com.mogumogu.moru.diary.controller;

import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import com.mogumogu.moru.diary.service.DiaryWalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryWalkController {
    @Autowired
    private DiaryWalkService diaryWalkService;

    @PostMapping("/workdiary")
    public int addDiaryWalk(@RequestBody DiaryWalkDTO diaryWalkDTO) {
        int result = 0;
        result = diaryWalkService.diaryWalkAdd(diaryWalkDTO);
        return result;
    }
}
