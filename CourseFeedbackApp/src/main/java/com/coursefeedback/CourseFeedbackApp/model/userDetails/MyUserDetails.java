package com.coursefeedback.CourseFeedbackApp.model.userDetails;

import com.coursefeedback.CourseFeedbackApp.model.user.Authority;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private final String userName;
    private final String password;
    private final List<Authority> grantedAuthority;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUserDetails(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.grantedAuthority = List.of(user.getAuthority());
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthority;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
