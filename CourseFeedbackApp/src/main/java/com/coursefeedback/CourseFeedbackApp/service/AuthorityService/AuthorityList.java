package com.coursefeedback.CourseFeedbackApp.service.AuthorityService;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthorityList implements AuthorityMemoryProvider{

    private List<Authority> authorityList = setAuthorities();

    public List<Authority> setAuthorities(){
        return Arrays.asList(new Authority("ROLE_ADMIN", 1), new Authority("ROLE_USER", 2));
    }
    @Override
    public List<Authority> getAuthorityList() {
        return authorityList;
    }
}
