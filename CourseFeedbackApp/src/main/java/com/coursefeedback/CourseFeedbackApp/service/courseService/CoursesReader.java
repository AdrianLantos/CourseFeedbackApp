package com.coursefeedback.CourseFeedbackApp.service.courseService;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CoursesReader implements CourseMemoryProvider{
    private List<Course> courses = new ArrayList<>();



    @Override
    public List<Course> getCourses() {
        return courses;
    }
}
