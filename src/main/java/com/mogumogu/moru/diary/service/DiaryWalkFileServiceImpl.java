package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.diary.dto.DiaryWalkFileDTO;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.entity.DiaryWalkFile;
import com.mogumogu.moru.diary.enumClass.DiaryWalkEnum;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import com.mogumogu.moru.diary.repository.DiaryWalkFileRepository;
import com.mogumogu.moru.diary.repository.DiaryWalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DiaryWalkFileServiceImpl implements DiaryWalkFileService{
    @Value("${project.upload.path}")
    private String uploadPath;
    private final char DIARY_WALK_N = DiaryWalkEnum.N.getIsBool();
    private final char DIARY_WALK_Y = DiaryWalkEnum.Y.getIsBool();
    @Autowired
    private DiaryWalkFileRepository diaryWalkFileRepository;
    @Autowired
    private DiaryWalkRepository diaryWalkRepository;
    @Override
    public List<DiaryWalkFileDTO> diaryWalkFileAdd(Integer dwNum, MultipartFile[] dwfFiles) throws DiaryWalkNotFoundException, IOException {

        List<DiaryWalkFileDTO> result= new ArrayList<>();
        DiaryWalk diaryWalk = diaryWalkRepository.findByDwNumAndDwDel(dwNum,DIARY_WALK_N).orElseThrow(DiaryWalkNotFoundException::new);
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        for (MultipartFile dwfFile : dwfFiles) {
            String fileExtension;
            fileExtension = dwfFile.getOriginalFilename().substring(dwfFile.getOriginalFilename().lastIndexOf('.'));
            String realFileName = UUID.randomUUID().toString() + fileExtension;
            String uploadDirectoryPath = uploadPath + "/diary/" + dwNum;
            File directory = new File(uploadDirectoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            dwfFile.transferTo(new File(uploadDirectoryPath + "/" + realFileName));
            DiaryWalkFile diaryWalkFile = DiaryWalkFile.builder()
                    .diaryWalk(diaryWalk)
                    .userInfo(userInfo)
                    .dwfOldFilename(dwfFile.getOriginalFilename())
                    .dwfRealFilename(realFileName)
                    .build();
            DiaryWalkFile checkDiaryFile = diaryWalkFileRepository.save(diaryWalkFile);
            result.add(DiaryWalkFileDTO.toDTO(checkDiaryFile));
        }
        return result;
    }
}
