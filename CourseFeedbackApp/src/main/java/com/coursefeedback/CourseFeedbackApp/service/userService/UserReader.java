package com.coursefeedback.CourseFeedbackApp.service.userService;

import com.coursefeedback.CourseFeedbackApp.model.user.User;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserReader implements UserMemoryProvider {
    List<User> users = new ArrayList<>();


    @Override
    public List<User> getUsers() {
        return users;
    }


}
