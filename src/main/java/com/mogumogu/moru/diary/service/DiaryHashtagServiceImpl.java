package com.mogumogu.moru.diary.service;

import com.mogumogu.moru.diary.entity.DiaryHashtag;
import com.mogumogu.moru.diary.entity.DiaryHashtagLink;
import com.mogumogu.moru.diary.entity.DiaryWalk;
import com.mogumogu.moru.diary.repository.DiaryHashtagLinkRepository;
import com.mogumogu.moru.diary.repository.DiaryHashtagRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DiaryHashtagServiceImpl implements DiaryHashtagService {
    @Autowired
    private DiaryHashtagRepository diaryHashtagRepository;
    @Autowired
    private DiaryHashtagLinkRepository diaryHashtagLinkRepository;

    @Override
    public int diaryHashtagAdd(Integer dwNum, String[] dhNames) {
        int result = 1;
        for (String dhName : dhNames) {
            DiaryHashtag diaryHashtag = diaryHashtagRepository.findByDhName(dhName).orElse(null);
            if (diaryHashtag == null) {
                diaryHashtag = DiaryHashtag.builder().dhName(dhName).build();
                diaryHashtag = diaryHashtagRepository.save(diaryHashtag);
            }
            DiaryHashtagLink diaryHashtagLink = DiaryHashtagLink.builder()
                    .diaryHashtag(diaryHashtag)
                    .diaryWalk(DiaryWalk.builder().dwNum(dwNum).build())
                    .build();
            diaryHashtagLinkRepository.save(diaryHashtagLink);
        }

        return result;
    }

    @Override
    public int hashtagLinkRemove(Integer dhlNum) {
        // TODO : 회원확인 필요
        diaryHashtagLinkRepository.deleteById(dhlNum);
        return 1;
    }
}
