package com.coursefeedback.CourseFeedbackApp.controller;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public List<Course> getCourses() {
        return service.getAllCourses();
    }

    @PutMapping("/{courseId}/user/{userId}")
    public void enrollUser(@PathVariable Integer courseId,
                           @PathVariable Integer userId) {
        service.enrollUser(courseId, userId);
    }

    @PutMapping
    public void createCourse(@RequestBody Course course) {
        service.createCourse(course);
    }
}
