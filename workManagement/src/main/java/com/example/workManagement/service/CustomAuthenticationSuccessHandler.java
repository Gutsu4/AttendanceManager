package com.example.workManagement.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final String REDIRECT_ADMIN = "/admin/admin_menu";
    private static final String REDIRECT_USER = "/user/mainmenu";
    @Override
    public void onAuthenticationSuccess(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, Authentication authentication) throws IOException, jakarta.servlet.ServletException {
        HttpSession session = request.getSession();
        int userId = Integer.parseInt(authentication.getName());

        // ユーザーの権限を取得
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // 管理者用ロール
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

        // ユーザーのロールに基づいてリダイレクト
        if (authorities.contains(adminAuthority)) {
            session.setAttribute("role", "ADMIN");
            response.sendRedirect(request.getContextPath() + REDIRECT_ADMIN);
        } else {
            session.setAttribute("role", "USER");
            response.sendRedirect(request.getContextPath() + REDIRECT_USER);
        }

        session.setAttribute("userId", userId);
        System.out.println("Logged in user: " + userId);
    }
}

