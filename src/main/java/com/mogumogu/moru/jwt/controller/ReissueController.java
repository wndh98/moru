package com.mogumogu.moru.jwt.controller;

import com.mogumogu.moru.jwt.entity.RefreshEntity;
import com.mogumogu.moru.jwt.jwt.JWTUtil;
import com.mogumogu.moru.jwt.repository.RefreshRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@ResponseBody
public class ReissueController {

    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    public ReissueController(JWTUtil jwtUtil,RefreshRepository refreshRepository) {
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
    }

    @PostMapping("reissue")
    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
        //get urtToken token
        String urtToken = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {

            if (cookie.getName().equals("urtToken")) {

                urtToken = cookie.getValue();
            }
        }

        if (urtToken == null) {

            //response status code
            return new ResponseEntity<>("urtToken token null", HttpStatus.BAD_REQUEST);
        }

        //expired check
        try {
            jwtUtil.isExpired(urtToken);
        } catch (ExpiredJwtException e) {

            //response status code
            return new ResponseEntity<>("urtToken token expired", HttpStatus.BAD_REQUEST);
        }

        // 토큰이 refresh인지 확인 (발급시 페이로드에 명시)
        String category = jwtUtil.getCategory(urtToken);

        if (!category.equals("urtToken")) {

            //response status code
            return new ResponseEntity<>("invalid urtToken token", HttpStatus.BAD_REQUEST);
        }

        //DB에 저장되어 있는지 확인
        Boolean isExist = refreshRepository.existsByUrtToken(urtToken);
        if (!isExist) {

            //response body
            return new ResponseEntity<>("invalid urtToken token", HttpStatus.BAD_REQUEST);
        }

        String uiId = jwtUtil.getUsername(urtToken);
        String uiNickname = jwtUtil.getUiNickname(urtToken);

        //make new JWT
        String newAccess = jwtUtil.createJwt("access", uiId, uiNickname, 600000L);
        String newUrtToken = jwtUtil.createJwt("urtToken", uiId, uiNickname, 86400000L);

        //Refresh 토큰 저장 DB에 기존의 Refresh 토큰 삭제 후 새 Refresh 토큰 저장
        refreshRepository.deleteByUrtToken(urtToken);
        addRefreshEntity(uiId, newUrtToken, 86400000L);

        //response
        response.setHeader("access", newAccess);
        response.addCookie(createCookie("urtToken", newUrtToken));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    private void addRefreshEntity(String uiId, String urtToken, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUiId(uiId);
        refreshEntity.setUrtToken(urtToken);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        //cookie.setSecure(true);
        //cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }
}


