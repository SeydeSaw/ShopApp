package com.example.shopapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JwtFilter extends GenericFilterBean {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String bearerToken = ((HttpServletRequest) servletRequest).getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            token = bearerToken.substring(7);
        }
        if (token != null && jwtService.validateToken(token)) {
            String username = jwtService.getUserNameFromToken(token);
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails,null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}