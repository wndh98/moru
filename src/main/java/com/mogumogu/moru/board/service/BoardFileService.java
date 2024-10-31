package com.mogumogu.moru.board.service;

import com.mogumogu.moru.board.dto.BoardFileDTO;
import com.mogumogu.moru.board.exception.BoardNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardFileService {
    List<String> BoardFileadd(String boType, Integer boNum, MultipartFile[] bfFiles) throws BoardNotFoundException, IOException;
}
