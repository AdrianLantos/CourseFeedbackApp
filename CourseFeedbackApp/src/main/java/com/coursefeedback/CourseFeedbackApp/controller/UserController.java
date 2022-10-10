package com.coursefeedback.CourseFeedbackApp.controller;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final Service service;

    public UserController(Service service) {
        this.service = service;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return service.getAllUsers();
    }
    @GetMapping("/{userId}/feedback")
    public List<Feedback> getUserFeedback(@PathVariable Integer userId){
        return service.getUserFeedback(userId);
    }
    @GetMapping("/{userId}")
    public List<Course> getUserCourses(@PathVariable Integer userId){
        return service.getUserCourses(userId);
    }

    @PutMapping("/{userId}/course/{courseId}")
    public void giveFeedback(@PathVariable Integer userId,
                             @PathVariable Integer courseId,
                             @RequestBody Feedback feedback){
        service.postFeedback(userId, courseId, feedback);
    }

    @PatchMapping("{userId}/feedback/{feedbackId}")
    public void editFeedback(@PathVariable Integer userId,
                             @RequestBody Feedback feedback,
                             @PathVariable Integer feedbackId){
        service.editFeedback(userId, feedback, feedbackId);
    }

    @DeleteMapping("/{userId}/feedback/{feedbackId}")
    public void deleteFeedback(@PathVariable Integer userid,
                              @PathVariable Integer feedbackId){
        service.deleteUserFeedback(userid, feedbackId);
    }
}
