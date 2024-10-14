package com.mogumogu.moru.jwt.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mogumogu.moru.jwt.dto.CustomUserDetails;
import com.mogumogu.moru.jwt.entity.RefreshEntity;
import com.mogumogu.moru.jwt.entity.UserInfoEntity;
import com.mogumogu.moru.jwt.repository.RefreshRepository;
import com.mogumogu.moru.jwt.dto.UserInfoDto;
import com.mogumogu.moru.jwt.repository.UserRepository;
import com.mogumogu.moru.user.repository.UserInfoRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


public class LoginFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public LoginFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RefreshRepository refreshRepository) {

        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserInfoDto userDto = new UserInfoDto();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ServletInputStream inputStream = request.getInputStream();
            String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            userDto = objectMapper.readValue(messageBody, UserInfoDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String uiId = userDto.getUiId();
        String uiPassword = userDto.getUiPassword();
        String uiNickname = userDto.getUiNickname();

        CustomAuthenticationToken authToken = new CustomAuthenticationToken(uiId, uiPassword,uiNickname);

        //token에 담은 검증을 위한 AuthenticationManager로 전달
        return authenticationManager.authenticate(authToken);

    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) {

        //유저 정보
        String uiId = authentication.getName();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(authentication.getPrincipal()+"authentication.getPrincipal()");
        String uiNickname = customUserDetails.getUiNickname();
        System.out.println(uiNickname+"uiNickname");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String uiRole = auth.getAuthority();

        //이 위에서 아이디,비밀번호 맞는지 검증

        //토큰 생성
        String access = jwtUtil.createJwt("access", uiId, uiNickname, uiRole, 600000L);
        String urtToken = jwtUtil.createJwt("urtToken", uiId, uiNickname, uiRole, 86400000L);

        //Refresh 토큰 저장
        addRefreshEntity(uiId, uiNickname, urtToken, 86400000L);

        //응답 설정
        response.setHeader("access", access);

        response.addCookie(createCookie("urtToken", urtToken));
        response.setStatus(HttpStatus.OK.value());

        System.out.println("successful");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
        response.setStatus(401);
        System.out.println("fail");
    }


    private void addRefreshEntity(String uiId, String uiNickname, String urtToken, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUiId(uiId);
        refreshEntity.setUiNickname(uiNickname);
        refreshEntity.setUrtToken(urtToken);
        refreshEntity.setExpiration(date.toString());

        //초기화 후 저장
        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}