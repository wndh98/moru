//package com.mogumogu.moru.auth;
//
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import java.security.Key;
//import java.util.Date;
//import java.util.stream.Collectors;
//@Slf4j
//@Component
//
//public class JwtTokenProvider {
//
//    private final Key key;
//    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public TokenInfoDto generateToken(Authentication authentication) {
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));
//        CustomUserDetail userDetail = (CustomUserDetail) authentication.getPrincipal();
//        Long userId = userDetails.getUserId();
//        String nickname = userDetails.getNickname();
//
//        long now = (new Date()).getTime();
//
//        Date accessTokenExpiresin = new Date(now + AuthConstant.ACCESS_TOKEN_EXPIRE_TIME);
//        String accessToken = Jwts.builder();
//
//    }
//
//    public String resolveToken(HttpServletRequest servletRequest) {
//        String bearerToken = servletRequest.getHeader("Authorization");
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
//            return bearerToken.substring(7);
//        }
//        return null;
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).b
//        }
//    }
//
//    public Authentication getAuthentication(String accessToken) {
//        Claims claims = parseClaims(accessToken);
//        if (claims.get("auth") == null) {
//            throw new RuntimeException("권한 정보가 없는 토근입니다.");
//        }
//        return null;
//    }
//
//
//}
//
