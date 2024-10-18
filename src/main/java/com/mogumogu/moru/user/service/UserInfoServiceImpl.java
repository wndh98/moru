package com.mogumogu.moru.user.service;

import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.jwt.repository.RefreshRepository;
import com.mogumogu.moru.user.exception.UserNotFoundException;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;
    private final RefreshRepository refreshRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserInfoDto detailsUserInfo(String uiId) {

        return UserInfoDto.toDto(userInfoRepository.findByUiId(uiId).orElseThrow());
    }

    @Override
    public int modifyUserInfo(UserInfoDto userInfoDto, String uiId) throws UserNotFoundException {

        int result = 1;

        if (!Objects.equals(userInfoDto.getUiId(), uiId)) {
            result = 0;
            return result;
        }
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId).orElseThrow(UserNotFoundException::new);
        userInfoEntity.setUiNickname(userInfoDto.getUiNickname());
        userInfoEntity.setUiEmail(userInfoDto.getUiEmail());
        userInfoEntity.setUiAge(userInfoDto.getUiAge());
        userInfoEntity.setUiGender(userInfoDto.getUiGender());
        userInfoEntity.setUiHeight(userInfoDto.getUiHeight());
        userInfoRepository.save(userInfoEntity);

        return result;
    }

    @Override
    public int removeUser(String uiId) throws UserNotFoundException {
        int result = 1;
        if (refreshRepository.findById(uiId).isPresent()) { //리프레시 토큰 삭제
            refreshRepository.deleteById(uiId);
        }
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId)
                .orElseThrow(UserNotFoundException::new);
        userInfoEntity.setUiDel('Y');
        userInfoRepository.save(userInfoEntity);
        return result;
    }

    public void updatePassword(String uiId, String uiPassword, String newUiPassword) throws UserNotFoundException {
        UserInfoEntity userInfoEntity = validatePassword(uiId, uiPassword);
        userInfoEntity.updatePassword(passwordEncoder.encode((newUiPassword)));
    }

    public UserInfoEntity validatePassword(String uiId, String uiPassword) throws UserNotFoundException {
        UserInfoEntity userInfoEntity = userInfoRepository.findByUiId(uiId)
                .orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(uiPassword, userInfoEntity.getUiPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
        }
        return userInfoEntity;
    }
}

