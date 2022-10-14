package com.coursefeedback.CourseFeedbackApp.service.userService;

import com.coursefeedback.CourseFeedbackApp.exception.BadRequest;
import com.coursefeedback.CourseFeedbackApp.exception.ResourceNotFound;
import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseRepository;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseService;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackRepository;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserMemoryProvider memoryProvider,
                       UserRepository userRepository
    ) {
        this.userRepository = userRepository;
        this.userRepository.saveAll(memoryProvider.getUsers());
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFound("User not found"));
    }

    public void createUser(String name) {
        if (userRepository.findAll()
                .stream().map(User::getName)
                .toList()
                .contains(name)) {
            System.err.println("User already exists");
            throw new BadRequest("User already exists");
        } else {
            User user = new User(name);
            userRepository.save(user);
        }
    }

    public List<Course> getUserCourses(Integer userId) {
        return userRepository.findById(userId)
                .stream()
                .map(User::getCourses)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFound("No courses found"));
    }

    public List<Feedback> getUserFeedback(Integer userId) {
        return userRepository.findById(userId)
                .stream()
                .map(User::getFeedbacks)
                .findAny()
                .orElse(List.of());
    }
}
