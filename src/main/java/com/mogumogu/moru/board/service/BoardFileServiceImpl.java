package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardFileDTO;
import com.mogumogu.moru.board.entity.BoardBase;
import com.mogumogu.moru.board.entity.BoardFile;
import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import com.mogumogu.moru.board.repository.BoardBaseRepository;
import com.mogumogu.moru.board.repository.BoardFileRepository;
import jakarta.transaction.Transactional;
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
public class BoardFileServiceImpl implements BoardFileService {
    @Value("${project.upload.path}")
    private String uploadPath;
    char BOARD_NOT_DEL = 'N';
    char BOARD_DEL = 'Y';
    @Autowired
    private BoardFileRepository boardFileRepository;
    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Override
    @Transactional
    public List<String> BoardFileadd(String boType, Integer boNum, MultipartFile[] bfFiles) throws BoardNotFoundException, IOException {

        List<String> result= new ArrayList<>();
        BoardBase boardBase = boardBaseRepository.findByBoNumAndBoDel(boNum, BOARD_NOT_DEL).orElseThrow(BoardNotFoundException::new);
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        for (MultipartFile bfFile : bfFiles) {
            String fileExtension;
            fileExtension = bfFile.getOriginalFilename().substring(bfFile.getOriginalFilename().lastIndexOf('.'));
            String realFileName = UUID.randomUUID().toString() + fileExtension;
            String uploadDirectoryPath = uploadPath + "/board/" + boNum;
            File directory = new File(uploadDirectoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            bfFile.transferTo(new File(uploadDirectoryPath + "/" + realFileName));
            BoardFile boardFile = BoardFile.builder()
                    .boardBase(boardBase)
                    .userInfo(userInfo)
                    .bfOldFilename(bfFile.getOriginalFilename())
                    .bfRealFilename(realFileName)
                    .bfType(boType)
                    .build();
            BoardFile checkBoardFile = boardFileRepository.save(boardFile);
            if (checkBoardFile.getBfNum() == null) {
                throw new BoardNotFoundException();
            }
            result.add(checkBoardFile.getBfRealFilename());
        }
        return result;
    }
}
