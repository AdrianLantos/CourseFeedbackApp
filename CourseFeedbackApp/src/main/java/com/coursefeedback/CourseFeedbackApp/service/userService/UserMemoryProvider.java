package com.coursefeedback.CourseFeedbackApp.service.userService;

import com.coursefeedback.CourseFeedbackApp.model.user.User;

import java.util.List;

public interface UserMemoryProvider {
    public List<User> getUsers();
}
