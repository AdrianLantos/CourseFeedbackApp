package com.coursefeedback.CourseFeedbackApp.service.userDetailsService;

import com.coursefeedback.CourseFeedbackApp.config.SecurityConfig;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.model.userDetails.MyUserDetails;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    public MyUserDetailsService() throws NoSuchMethodException {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException(String.format("Authentication for user %s failed", username)));
        user.get().setPassword(passwordEncoder.encode(user.get().getPassword()));
        return user.map(MyUserDetails::new).get();
    }
}
