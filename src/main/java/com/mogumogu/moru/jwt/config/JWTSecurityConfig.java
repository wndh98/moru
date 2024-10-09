package com.mogumogu.moru.jwt.config;

import com.mogumogu.moru.jwt.jwt.CustomLogoutFilter;
import com.mogumogu.moru.jwt.jwt.JWTFilter;
import com.mogumogu.moru.jwt.jwt.JWTUtil;
import com.mogumogu.moru.jwt.jwt.LoginFilter;
import com.mogumogu.moru.jwt.repository.RefreshRepository;
//import com.mogumogu.moru.user.oauth2.CustomSuccessHandler;
//import com.mogumogu.moru.user.service.CustomOAuth2UserService;
//import com.mogumogu.moru.user.userjwt.UserJWTFilter;
//import com.mogumogu.moru.user.userjwt.UserJWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity

public class JWTSecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;
//    private final CustomOAuth2UserService customOAuth2UserService;
//    private final CustomSuccessHandler customSuccessHandler;
//    private final UserJWTUtil userJwtUtil;

//    , CustomOAuth2UserService customOAuth2UserService, CustomSuccessHandler customSuccessHandler, UserJWTUtil userJwtUtil
    public JWTSecurityConfig(AuthenticationConfiguration authenticationConfiguration, JWTUtil jwtUtil, RefreshRepository refreshRepository) {

        this.authenticationConfiguration = authenticationConfiguration;
        this.jwtUtil = jwtUtil;
        this.refreshRepository = refreshRepository;
//        this.customOAuth2UserService = customOAuth2UserService;
//        this.customSuccessHandler = customSuccessHandler;
//        this.userJwtUtil = userJwtUtil;
    }

    //AuthenticationManager Bean 등록
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors((corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration configuration = new CorsConfiguration();

                        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
                        configuration.setAllowedMethods(Collections.singletonList("*"));
                        configuration.setAllowCredentials(true);
                        configuration.setAllowedHeaders(Collections.singletonList("*"));
                        configuration.setMaxAge(3600L);

                        configuration.setExposedHeaders(Collections.singletonList("Set-Cookie"));
                        configuration.setExposedHeaders(Collections.singletonList("Authorization"));

                        return configuration;
                    }
                })));

        //csrf disable
        http
                .csrf((auth) -> auth.disable());

        //from 로그인 방식 disable
        http
                .formLogin((auth) -> auth.disable());

        //http basic 인증 방식 disable
        http
                .httpBasic((auth) -> auth.disable());

        //JWTFilter 추가
//        http
//                .addFilterBefore(new UserJWTFilter(userJwtUtil), UsernamePasswordAuthenticationFilter.class);

        //oauth2
//        http
//                .oauth2Login((oauth2) -> oauth2
//                        .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
//                                .userService(customOAuth2UserService))
//                        .successHandler(customSuccessHandler)
//                );

        //경로별 인가 작업
        http
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/login","/users").permitAll()
                        .requestMatchers("/reissue").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated());

        http
                .addFilterBefore(new JWTFilter(jwtUtil), LoginFilter.class);

        http
                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration), jwtUtil, refreshRepository), UsernamePasswordAuthenticationFilter.class);

        http
                .addFilterBefore(new CustomLogoutFilter(jwtUtil, refreshRepository), LoginFilter.class);

        //세션 설정
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
