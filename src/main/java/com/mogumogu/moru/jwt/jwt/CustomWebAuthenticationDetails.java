package com.mogumogu.moru.jwt.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
    private String uiNickname;

    // 사용자가 전달하는 추가적인 파라미터들을 저장하는 클래스
    public CustomWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        uiNickname =  request.getParameter("uiNickname");
    }

    public String getUiNickname() {
        return uiNickname;
    }
}
