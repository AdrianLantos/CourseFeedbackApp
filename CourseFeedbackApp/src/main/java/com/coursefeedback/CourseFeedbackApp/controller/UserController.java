package com.coursefeedback.CourseFeedbackApp.controller;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserController {

    private final Service service;

    public UserController(Service service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping("/users/courses")
    public List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    @GetMapping("/users/{userId}")
    public List<Course> getUserCourses(@PathVariable Integer userId) {
        return service.getUserCourses(userId);
    }

    @GetMapping("/users/{userId}/feedback/{courseId}")
    public List<Feedback> getUserFeedbackForCourse(@PathVariable Integer userId,
                                                   @PathVariable Integer courseId) {
        return service.userFeedbackForCourse(userId, courseId);
    }

    @PutMapping("/users/{userId}/courses/{courseId}")
    public void enrollUser(@PathVariable Integer courseId,
                           @PathVariable Integer userId) {
        service.enrollUser(courseId, userId);
    }

    @PutMapping("/users/{userId}/course/{courseId}")
    public void giveFeedback(@PathVariable Integer userId,
                             @PathVariable Integer courseId,
                             @RequestBody Feedback feedback) {
        service.postFeedback(userId, courseId, feedback);
    }

    @PatchMapping("/users/{userId}/course/{feedbackId}")
    public void editFeedback(@PathVariable Integer userId,
                             @RequestBody Feedback feedback,
                             @PathVariable Integer feedbackId) {
        service.editFeedback(userId, feedback, feedbackId);
    }

    @DeleteMapping("/users/feedback/{feedbackId}")
    public void deleteFeedback(@PathVariable Integer feedbackId) {
        service.deleteFeedback(feedbackId);
    }
}
//    @GetMapping("/users/{userId}/feedback")
//    public List<Feedback> getUserFeedback(@PathVariable Integer userId) {
//        return service.getUserFeedback(userId);
//    }
//
//    @GetMapping("/feedback")
//    public List<Feedback> getAllFeedback(){
//        return service.getAllFeedback();
//    }
//
//    @GetMapping("/courses")
//    public List<Course> getCourses(){
//        return service.getAllCourses();
//    }
//
//    @PutMapping("/courses/addCourse")
//    public void createCourse(@RequestBody Course course){
//        service.createCourse(course);
//    }
//
//    @PutMapping("/users/addUser")
//    public void createUser(@RequestParam(required = false) String name){
//        service.createUser(name);
//    }
////
//    @GetMapping("/feedback/{courseId}")
//    public List<Feedback> getFeedbackForCourse(@PathVariable Integer courseId){
//        return service.getFeedbackForCourse(courseId);
//    }
//
