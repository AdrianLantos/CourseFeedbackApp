package com.coursefeedback.CourseFeedbackApp.service.AuthorityService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
    Authority findByAuthority(String authorityType);
}
