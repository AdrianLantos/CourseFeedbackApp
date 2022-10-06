package com.coursefeedback.CourseFeedbackApp.service.courseService;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
@Component
public class CoursesReader implements CourseMemoryProvider{
    private List<Course> courses = readCourses();

    private List<Course> readCourses(){
        try {
            return Files.lines(Path.of("src/main/resources/courses.txt"))
                    .map(Course::new)
                    .toList();
        }catch (IOException e){
            throw new RuntimeException("File not found");
        }
    }

    @Override
    public List<Course> getCourses() {
        return courses;
    }
}
