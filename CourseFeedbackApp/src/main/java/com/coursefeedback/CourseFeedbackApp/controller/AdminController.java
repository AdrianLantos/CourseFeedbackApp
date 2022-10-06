package com.coursefeedback.CourseFeedbackApp.controller;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final Service service;

    public AdminController(Service service) {
        this.service = service;
    }
    @GetMapping("/users")
    public List<User> getUsers(){
        return service.getAllUsers();
    }

    @GetMapping("/feedback")
    public List<Feedback> getFeedback(){
        return service.getAllFeedback();
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return service.getAllCourses();
    }

    @PutMapping("/courses/addCourse")
    public void createCourse(@RequestBody Course course){
        service.createCourse(course);
    }

    @PutMapping("/users/addUser")
    public void createUser(@RequestParam(required = false) String name){
        service.createUser(name);
    }

    @PutMapping("/courses/{courseId}/users/{userId}")
    public void enrollUser(@PathVariable Integer courseId,
                           @PathVariable Integer userId){
        service.enrollUser(courseId, userId);
    }

    @GetMapping("/feedback/{courseId}")
    public List<Feedback> getFeedbackForCourse(@PathVariable Integer courseId){
        return service.getFeedbackForCourse(courseId);
    }

    @DeleteMapping("/feedback/{feedbackId}")
    public void deleteFeedback(@PathVariable Integer feedbackId){
        service.deleteFeedback(feedbackId);
    }
}
