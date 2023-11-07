package com.example.workManagement.service;

import com.example.workManagement.repository.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserManager userManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String user_id = authentication.getName();
        String password = (String) authentication.getCredentials();

        //useridとpassでログイン認証処理
        if (userManager.login(user_id, password)) {

            //DBからroleを取得する
            String role = userManager.getUserRoleById(user_id);
            List<GrantedAuthority> authorities = new ArrayList<>();

            //roleがadmin
            if(role!=null){
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
            }

            return new UsernamePasswordAuthenticationToken(user_id, password, authorities);

        } else {
            throw new BadCredentialsException("ログインIDまたはパスワードが間違っています。");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
