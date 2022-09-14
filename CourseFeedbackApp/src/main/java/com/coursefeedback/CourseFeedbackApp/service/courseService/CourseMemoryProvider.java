package com.coursefeedback.CourseFeedbackApp.service.courseService;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;

import java.util.List;

public interface CourseMemoryProvider {
    public List<Course> getCourses();
}
