package com.coursefeedback.CourseFeedbackApp.service.feedbackService;

import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class FeedbackReader implements FeedbackMemoryProvider{
    private List<Feedback> feedbackList =new ArrayList<>();
        @Override
    public List<Feedback> getFeedback() {
        return feedbackList;
    }
}
