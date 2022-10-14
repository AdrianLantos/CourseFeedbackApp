package com.coursefeedback.CourseFeedbackApp.service.feedbackService;

import com.coursefeedback.CourseFeedbackApp.exception.ResourceNotFound;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseService;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserService userService;
    private final CourseService courseService;

    public FeedbackService(FeedbackRepository repository,
                           UserService userService,
                           CourseService courseService,
                           FeedbackMemoryProvider memoryProvider) {
        this.feedbackRepository = repository;
        this.userService = userService;
        this.courseService = courseService;

        repository.saveAll(memoryProvider.getFeedback());
    }

    public Feedback findFeedback(Integer feedbackId){
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new ResourceNotFound("Feedback nor found"));
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public List<Feedback> getFeedbackForCourse(Integer courseId) {
        return feedbackRepository.findAll()
                .stream()
                .filter(feedback -> courseService.findCourse(courseId)
                        .getFeedbackList()
                        .contains(feedback.getCourse()))
                .toList();
    }

    public void deleteFeedback(Integer feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public void postFeedback(Integer userId, Integer courseId, Feedback feedback) {
        feedback.setCourse(courseService.findCourse(courseId));
        feedback.setAuthor(userService.findUser(userId));
        feedbackRepository.save(feedback);
    }

    public void editFeedback(Integer userId, Feedback feedback, Integer feedbackId) {
        feedback.setId(feedbackId);
        feedback.setCourse(findFeedback(feedbackId).getCourse());
        feedback.setAuthor(userService.findUser(userId));
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getUserFeedbackForCourse(Integer userId, Integer courseId) {
        List<Feedback> userFeedbackList = userService.findUser(userId).getFeedbacks();
        List<Feedback> courseFeedbackList = courseService.findCourse(courseId).getFeedbackList();
        List<Feedback> result = new ArrayList<>();
        for (Feedback feedback : courseFeedbackList) {
            if (userFeedbackList.contains(feedback)) {
                result.add(feedback);
            }
        }
        return result;
    }
}
