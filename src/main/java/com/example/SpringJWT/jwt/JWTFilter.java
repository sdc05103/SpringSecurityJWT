package com.example.SpringJWT.jwt;

import com.example.SpringJWT.DTO.CustomUserDetails;
import com.example.SpringJWT.entity.UserEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if(authorization == null || !authorization.startsWith("Bearer")){
            System.out.println("token null");
            filterChain.doFilter(request, response);

            return;
        }
        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰 획득
        String token = authorization.split(" ")[1];
        //토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request,response);

            return;
        }

        //토큰에서 username, role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //userEntity를 생성해 값을 setting, 비밀번호엔 아무거나
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("temppassword");
        userEntity.setRole(role);

        //userDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request,response);

    }
}
