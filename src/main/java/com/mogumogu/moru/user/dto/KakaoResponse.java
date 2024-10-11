package com.mogumogu.moru.user.dto;


import java.util.Map;

public class KakaoResponse implements OAuth2Response {

    private final Map<String, Object> attribute;

    public KakaoResponse(Map<String, Object> attribute) {

        this.attribute = attribute;
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return attribute.get("uiId").toString();
    }


    @Override
    public String getUiNickname() {
        return attribute.get("uiNickname").toString();
    }
}
