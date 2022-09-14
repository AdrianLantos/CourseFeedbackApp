package com.coursefeedback.CourseFeedbackApp.service.courseService;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
