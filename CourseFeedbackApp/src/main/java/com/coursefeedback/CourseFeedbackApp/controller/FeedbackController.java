package com.coursefeedback.CourseFeedbackApp.controller;

import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService service;

    public FeedbackController(FeedbackService service) {
        this.service = service;
    }

    @PutMapping("/{userId}/course/{courseId}")
    public void giveFeedback(@PathVariable Integer userId,
                             @PathVariable Integer courseId,
                             @RequestBody Feedback feedback) {
        service.postFeedback(userId, courseId, feedback);
    }

    @PatchMapping("/{userId}/course/{feedbackId}")
    public void editFeedback(@PathVariable Integer userId,
                             @RequestBody Feedback feedback,
                             @PathVariable Integer feedbackId) {
        service.editFeedback(userId, feedback, feedbackId);
    }

    @DeleteMapping("/{feedbackId}")
    public void deleteFeedback(@PathVariable Integer feedbackId) {
        service.deleteFeedback(feedbackId);
    }

    @GetMapping("/{courseId}/user/{userId}")
    public List<Feedback> getUserFeedbackForCourse(@PathVariable Integer userId,
                                                   @PathVariable Integer courseId) {
        return service.getUserFeedbackForCourse(userId, courseId);
    }
}
