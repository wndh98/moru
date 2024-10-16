package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.diary.dto.DiaryWalkDTO;
import com.mogumogu.moru.diary.entity.DiaryHashtag;
import com.mogumogu.moru.diary.entity.DiaryHashtagLink;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.enumClass.DiaryWalkEnum;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import com.mogumogu.moru.diary.repository.DiaryHashtagLinkRepository;
import com.mogumogu.moru.diary.repository.DiaryHashtagRepository;
import com.mogumogu.moru.diary.repository.DiaryWalkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryWalkServiceImpl implements DiaryWalkService {
    private final char DIARY_WALK_N = DiaryWalkEnum.N.getIsBool();
    private final char DIARY_WALK_Y = DiaryWalkEnum.Y.getIsBool();
    @Autowired
    private DiaryWalkRepository diaryWalkRepository;

    @Override
    public Integer diaryWalkAdd(DiaryWalkDTO diaryWalkDTO) {
        // TODO : JWT 회원 확인 필요
        Integer dwNum = null;
        DiaryWalk diaryWalk = diaryWalkRepository.save(DiaryWalk.toEntity(diaryWalkDTO));
        if (diaryWalk.getDwNum() == null) {
            return dwNum;
        }
        dwNum = diaryWalk.getDwNum();
        return dwNum;
    }

    @Override
    public int diaryRemove(Integer dwNum) throws DiaryWalkNotFoundException {
        // TODO : JWT 회원 확인 필요
        int result = 1;
        DiaryWalk diaryWalk = diaryWalkRepository.findById(dwNum).orElseThrow(DiaryWalkNotFoundException::new);
        diaryWalk.setDwDel(DIARY_WALK_Y);
        diaryWalkRepository.save(diaryWalk);
        return result;
    }

    @Override
    public int diaryModify(DiaryWalkDTO diaryWalkDTO) throws DiaryWalkNotFoundException {
        // TODO : JWT 회원 확인 필요
        int result = 1;
        DiaryWalk diaryWalk = diaryWalkRepository.findById(diaryWalkDTO.getDwNum()).orElseThrow(DiaryWalkNotFoundException::new);
        diaryWalk.setDwTitle(diaryWalk.getDwTitle());
        diaryWalk.setDwContent(diaryWalk.getDwContent());
        diaryWalk.setDwPrivate(diaryWalk.getDwPrivate());
        diaryWalkRepository.save(diaryWalk);
        return result;
    }

    @Override
    public List<DiaryWalkDTO> diaryWalkList(Pageable pageable, String uiId) {
        Page<DiaryWalk> pageList;
        if (uiId != null) {
            // TODO : 보인 아이디일경우 공개 비공개 상관없이 볼수있게 수정
            if (uiId == "보인아이디") {
                pageList = diaryWalkRepository.findByDwDelAndUiId(DIARY_WALK_N, uiId, pageable);
            } else {
                pageList = diaryWalkRepository.findByDwDelAndDwPrivateAndUiId(DIARY_WALK_N, DIARY_WALK_N, uiId, pageable);
            }
        } else {
            pageList = diaryWalkRepository.findByDwDelAndDwPrivate(DIARY_WALK_N, DIARY_WALK_N, pageable);
        }
        return pageList.stream().map(DiaryWalkDTO::toDTO).collect(Collectors.toList());
    }

    @Override
    public DiaryWalkDTO diaryWalkDetails(Integer dwNum) throws DiaryWalkNotFoundException {
        DiaryWalkDTO diaryWalkDTO = null;
        DiaryWalk diaryWalk = null;
        diaryWalk = diaryWalkRepository.findByDwNumAndDwDel(dwNum, DIARY_WALK_N).orElseThrow(DiaryWalkNotFoundException::new);
        // TODO : 아이디 수정 필요
        if (diaryWalk.getUserInfo().getUiId().equals("아이디")) diaryWalkDTO = DiaryWalkDTO.toDTO(diaryWalk);
        if (diaryWalk.getDwPrivate() == DIARY_WALK_Y) return diaryWalkDTO;
        diaryWalkDTO = DiaryWalkDTO.toDTO(diaryWalk);
        return diaryWalkDTO;
    }

    @Override
    public int todayDiaryWalkCheck() {
        // TODO : JWT 수정
        int result = 0;
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        boolean check = diaryWalkRepository.existsByDwDelAndUserInfoAndDwRegist(DIARY_WALK_N, userInfo, LocalDateTime.now());
        if(check) result = 1;
        return result;
    }
}
