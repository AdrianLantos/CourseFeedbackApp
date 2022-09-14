package com.coursefeedback.CourseFeedbackApp.service.AuthorityService;

import com.coursefeedback.CourseFeedbackApp.model.user.Authority;
import java.util.List;


public interface AuthorityMemoryProvider {
    public List<Authority> getAuthorityList();
}
