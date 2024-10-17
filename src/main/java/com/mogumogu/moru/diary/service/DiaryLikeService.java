package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.diary.exception.DiaryWalkNotFoundException;

public interface DiaryLikeService {
    int diaryLikeAdd(int dwNum);

    int diaryLikeRemove(int dwNum) throws DiaryWalkNotFoundException;
}
