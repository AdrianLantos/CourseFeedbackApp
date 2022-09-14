package com.coursefeedback.CourseFeedbackApp.service.AuthorityService;

import com.coursefeedback.CourseFeedbackApp.model.user.Authority;
import com.coursefeedback.CourseFeedbackApp.model.user.AuthorityTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthorityType(AuthorityTypes authorityTypes);
}
