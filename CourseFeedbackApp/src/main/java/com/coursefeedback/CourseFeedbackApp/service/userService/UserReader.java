package com.coursefeedback.CourseFeedbackApp.service.userService;

import com.coursefeedback.CourseFeedbackApp.model.user.User;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
@Component
public class UserReader implements UserMemoryProvider {
    List<User> users = readUsers();

    private List<User> readUsers(){
        try {
            return Files.lines(Path.of("src/main/resources/users.txt"))
                    .map(User::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException("File not found");
        }
    }
    @Override
    public List<User> getUsers() {
        return users;
    }


}
