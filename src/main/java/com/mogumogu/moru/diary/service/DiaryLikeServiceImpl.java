package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.board.entity.UserInfo;
import com.mogumogu.moru.diary.entity.DiaryLike;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;
import com.mogumogu.moru.diary.repository.DiaryLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryLikeServiceImpl implements DiaryLikeService {
    @Autowired
    private DiaryLikeRepository diaryLikeRepository;
    @Override
    public int diaryLikeAdd(int dwNum) {
        DiaryWalk diaryWalk = DiaryWalk.builder().dwNum(dwNum).build();
        // TODO : JWT 추가후 수정
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        DiaryLike diaryLike = DiaryLike.builder().diaryWalk(diaryWalk).userInfo(userInfo).build();
        diaryLikeRepository.save(diaryLike);
        return 1;
    }

    @Override
    public int diaryLikeRemove(int dwNum) throws DiaryWalkNotFoundException {
        DiaryWalk diaryWalk = DiaryWalk.builder().dwNum(dwNum).build();
        // TODO : JWT 추가후 수정
        UserInfo userInfo = UserInfo.builder().uiId("test").build();
        DiaryLike diaryLike = diaryLikeRepository.findByUserInfoAndDiaryWalk(userInfo,diaryWalk).orElseThrow(DiaryWalkNotFoundException::new);
        diaryLikeRepository.deleteById(diaryLike.getDlNum());
        return 1;
    }
}
