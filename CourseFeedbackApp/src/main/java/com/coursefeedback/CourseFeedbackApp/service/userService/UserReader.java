package com.coursefeedback.CourseFeedbackApp.service.userService;

import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.AuthorityService.AuthorityMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.AuthorityService.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class UserReader implements UserMemoryProvider {
    private final AuthorityRepository authorityRepository;
    List<User> users = new ArrayList<>();

    public UserReader(AuthorityMemoryProvider authorityMemoryProvider, AuthorityRepository repository){
        this.authorityRepository = repository;
        repository.saveAll(authorityMemoryProvider.getAuthorityList());
        createAdmin();
    }

    public void createAdmin() {
        User admin = new User("admin", "admin", "admin");
        admin.setAuthority(authorityRepository.findById(1).get());
        authorityRepository.findById(1).orElseThrow(() -> new RuntimeException("WRONG AUTHORITY")).getUsers().add(admin);
    }
    @Override
    public List<User> getUsers() {
        return users;
    }


}
