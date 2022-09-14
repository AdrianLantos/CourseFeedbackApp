package com.coursefeedback.CourseFeedbackApp.service.feedbackService;

import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;

import java.util.List;

public interface FeedbackMemoryProvider {
    public List<Feedback> getFeedback();
}
