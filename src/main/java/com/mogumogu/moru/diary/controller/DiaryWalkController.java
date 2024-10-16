package com.mogumogu.moru.diary.controller;

import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import com.mogumogu.moru.diary.dto.DiaryWalkFileDTO;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import com.mogumogu.moru.diary.service.DiaryWalkFileService;
import com.mogumogu.moru.diary.service.DiaryWalkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class DiaryWalkController {
    @Autowired
    private DiaryWalkService diaryWalkService;
    @Autowired
    private DiaryWalkFileService diaryWalkFileService;

    @PostMapping("/diarywalk")
    public int addDiaryWalk(@RequestBody DiaryWalkDTO diaryWalkDTO) {
        int result = 0;
        result = diaryWalkService.diaryWalkAdd(diaryWalkDTO);
        return result;
    }

    @DeleteMapping("/diarywalk/{dwNum}")
    public int removeDiaryWalk(@PathVariable("dwNum") Integer dwNum) {
        int result = 0;
        try {
            result = diaryWalkService.diaryRemove(dwNum);
        } catch (DiaryWalkNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @PutMapping("/diarywalk")
    public int modifyDiaryWalk(@RequestBody DiaryWalkDTO diaryWalkDTO) {
        int result = 0;
        try {
            result = diaryWalkService.diaryModify(diaryWalkDTO);
        } catch (DiaryWalkNotFoundException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @GetMapping("/diarywalk")
    public List<DiaryWalkDTO> listDiaryWalk(@RequestParam(name = "uiId", required = false) String uiId, Pageable pageable) {
        List<DiaryWalkDTO> list = null;
        list = diaryWalkService.diaryWalkList(pageable, uiId);
        return list;
    }

    @GetMapping("/diarywalk/{dwNum}")
    public DiaryWalkDTO detailsDiaryWalk(@PathVariable("dwNum") Integer dwNum) {
        DiaryWalkDTO diaryWalkDTO = null;
        try {
            diaryWalkDTO = diaryWalkService.diaryWalkDetails(dwNum);
        } catch (DiaryWalkNotFoundException e) {
            throw new RuntimeException(e);
        }
        return diaryWalkDTO;
    }

    @PostMapping("/diarywalkfile/{dwNum}")
    public List<DiaryWalkFileDTO> addDiaryWalkFile(@PathVariable("dwNum") Integer dwNum, @RequestPart("dwfFiles") MultipartFile[] dwfFiles) {
        List<DiaryWalkFileDTO> list = null;
        try {
            list = diaryWalkFileService.diaryWalkFileAdd(dwNum, dwfFiles);
        } catch (IOException | DiaryWalkNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    @DeleteMapping("/hashtaglink")
    public int removeHashtagLink(){
        return 0;
    }

}
