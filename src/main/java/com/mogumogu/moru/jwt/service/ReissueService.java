package com.mogumogu.moru.jwt.service;
import com.mogumogu.moru.jwt.entity.RefreshEntity;
import com.mogumogu.moru.jwt.jwt.JWTUtil;
import com.mogumogu.moru.jwt.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class ReissueService {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    @Autowired
    public ReissueService(JWTUtil jwtUtil, RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    public ResponseEntity<?> reissueToken(HttpServletRequest request, HttpServletResponse response) {
        String urtToken = extractUrtToken(request);

        if (urtToken == null) {
            return new ResponseEntity<>("urtToken null", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 만료되었는지 확인
        try {
            jwtUtil.isExpired(urtToken);
        } catch (ExpiredJwtException e) {
            return new ResponseEntity<>("urtToken expired", HttpStatus.BAD_REQUEST);
        }

        String category = jwtUtil.getCategory(urtToken);
        if (!"urtToken".equals(category)) {
            return new ResponseEntity<>("invalid urtToken", HttpStatus.BAD_REQUEST);
        }

        // DB에 토큰이 존재하는지 확인
        if (!refreshRepository.existsByUrtToken(urtToken)) {
            return new ResponseEntity<>("invalid urtToken", HttpStatus.BAD_REQUEST);
        }

        // 새로운 토큰 생성
        String uiId = jwtUtil.getUiId(urtToken);
        String uiNickname = jwtUtil.getUiNickname(urtToken);
        String uiRole = jwtUtil.getUiRole(urtToken);

        String newAccess = jwtUtil.createJwt("access", uiId, uiNickname, uiRole, 600000L);
        String newUrtToken = jwtUtil.createJwt("urtToken", uiId, uiNickname, uiRole, 86400000L);

        // 데이터베이스에서 리프레시 토큰 업데이트
        updateRefreshToken(uiId, newUrtToken, uiNickname, 86400000L, urtToken);

        // 응답 헤더와 쿠키 설정
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("urtToken", newUrtToken));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private String extractUrtToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("urtToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }


    private void updateRefreshToken(String uiId, String newUrtToken, String uiNickname, Long expiredMs, String oldUrtToken) {
        refreshRepository.deleteByUrtToken(oldUrtToken);
        addRefreshEntity(uiId, newUrtToken, uiNickname, expiredMs);
    }


    private void addRefreshEntity(String uiId, String urtToken, String uiNickname, Long expiredMs) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiredMs);
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUiId(uiId);
        refreshEntity.setUiNickname(uiNickname);
        refreshEntity.setUrtToken(urtToken);
        refreshEntity.setExpiration(expirationDate.toString());

        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24 * 60 * 60);
        cookie.setHttpOnly(true);
        return cookie;
    }
}
