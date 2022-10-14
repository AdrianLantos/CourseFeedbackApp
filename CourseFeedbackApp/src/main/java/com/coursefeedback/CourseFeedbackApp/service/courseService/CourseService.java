package com.coursefeedback.CourseFeedbackApp.service.courseService;

import com.coursefeedback.CourseFeedbackApp.exception.BadRequest;
import com.coursefeedback.CourseFeedbackApp.exception.ResourceNotFound;
import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UserService userService;

    public CourseService(CourseRepository repository,
                         UserService userService,
                         CourseMemoryProvider memoryProvider){

        this.courseRepository = repository;
        this.userService = userService;
        this.courseRepository.saveAll(memoryProvider.getCourses());
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourse(Integer courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFound("Course not Found"));
    }

    public void createCourse(Course course) {
        if (courseRepository.findAll().contains(course)) {
            throw new BadRequest("Course already exists");
        }
        courseRepository.save(course);
    }
    public void enrollUser(Integer courseId, Integer userId) {
        Course course = findCourse(courseId);
        User user = userService.findUser(userId);

        if (user.getCourses().contains(course)) {
            throw new BadRequest("User is already enrolled");
        } else {
            course.addUser(user);
            courseRepository.save(course);
        }
    }
}
