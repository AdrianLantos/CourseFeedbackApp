package com.coursefeedback.CourseFeedbackApp.service.feedbackService;

import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
}
