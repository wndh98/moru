package com.mogumogu.moru.user.service;

import com.mogumogu.moru.user.dto.KakaoResponse;
import com.mogumogu.moru.user.dto.OAuth2Response;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserInfoRepository userInfoRepository;

    public CustomOAuth2UserService(UserInfoRepository userInfoRepository) {

        this.userInfoRepository = userInfoRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        System.out.println(oAuth2User+"oAuth2User");

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("kakao")) {
            oAuth2Response = new KakaoResponse(oAuth2User.getAttributes());
        } else {

            return null;
        }

        //리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디값을 만듬
        String uiId = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
        String uiNickname = oAuth2Response.getUiNickname();
        UserInfoEntity existData = userInfoRepository.findByUiId(uiId).orElseThrow();
        existData = userInfoRepository.findByUiNickname(uiNickname).orElseThrow();
        //데이터가 존재하지 않는 경우
        if (existData == null) {

            UserInfoEntity userInfoEntity = new UserInfoEntity();
            userInfoEntity.setUiId(uiId);
            userInfoEntity.setUiNickname(uiNickname);
            userInfoEntity.setUiRole("ROLE_USER");

            userInfoRepository.save(userInfoEntity);

            UserInfoDto userInfoDTO = new UserInfoDto();
            userInfoDTO.setUiId(uiId);
            userInfoDTO.setUiNickname(uiNickname);
            userInfoDTO.setUiRole("ROLE_USER");

            return new CustomOAuth2User(userInfoDTO);
        }
        //데이터가 존재하는 경우
        else {

            existData.setUiNickname(oAuth2Response.getUiNickname());

            userInfoRepository.save(existData);

            UserInfoDto userInfoDTO = new UserInfoDto();
            userInfoDTO.setUiId(uiId);
            userInfoDTO.setUiNickname(oAuth2Response.getUiNickname());
            userInfoDTO.setUiRole("ROLE_USER");

            return new CustomOAuth2User(userInfoDTO);
        }
    }
}