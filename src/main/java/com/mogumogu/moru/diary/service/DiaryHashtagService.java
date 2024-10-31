package com.mogumogu.moru.diary.service;

public interface DiaryHashtagService {
    int diaryHashtagAdd(Integer dwNum, String[] dhNames);

    int hashtagLinkRemove(Integer dhlNum);
}
