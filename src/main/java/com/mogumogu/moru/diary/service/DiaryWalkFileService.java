package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.diary.dto.DiaryWalkFileDTO;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DiaryWalkFileService {
    List<DiaryWalkFileDTO> diaryWalkFileAdd(Integer dwNum, MultipartFile[] dwfFiles) throws DiaryWalkNotFoundException, IOException;
}
