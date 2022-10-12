package com.coursefeedback.CourseFeedbackApp.service;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseRepository;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackRepository;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserRepository;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final CourseRepository courseRepository;

    public Service(FeedbackMemoryProvider feedbackMemoryProvider,
                   UserMemoryProvider memoryProvider,
                   CourseRepository courseRepository,
                   UserRepository userRepository,
                   FeedbackRepository feedbackRepository,
                   CourseMemoryProvider courseMemoryProvider
    ) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
        this.courseRepository = courseRepository;

        this.feedbackRepository.saveAll(feedbackMemoryProvider.getFeedback());
        this.userRepository.saveAll(memoryProvider.getUsers());
        this.courseRepository.saveAll(courseMemoryProvider.getCourses());
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public void createCourse(Course course) {
        if (courseRepository.findAll().contains(course)) {
            throw new RuntimeException("Course already exists");
        }
        courseRepository.save(course);
    }

    public void createUser(String name) {
        if (userRepository.findAll().stream().map(User::getName).toList().contains(name)) {
            System.err.println("User already exists");
            throw new RuntimeException("User already exists");
        } else {
            User user = new User(name);
            userRepository.save(user);
        }
    }

    public void enrollUser(Integer courseId, Integer userId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not Found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getCourses().contains(course)) {
            throw new RuntimeException("User is already enrolled");
        } else {
            course.addUser(user);
            courseRepository.save(course);
        }
    }

    public List<Feedback> getFeedbackForCourse(Integer courseId) {
        return feedbackRepository.findAll()
                .stream()
                .filter(feedback -> courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("No matching Course"))
                        .getFeedbackList()
                        .contains(feedback.getCourse()))
                .toList();
    }

    public void deleteFeedback(Integer feedbackId) {
        Feedback deletedFeedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> {throw new RuntimeException("No Feedback Found");});
        feedbackRepository.delete(deletedFeedback);
    }

    public List<Course> getUserCourses(Integer userId) {
        return userRepository.findById(userId)
                .stream()
                .map(User::getCourses)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No courses found"));
    }

    public List<Feedback> getUserFeedback(Integer userId) {
        return userRepository.findById(userId)
                .stream()
                .map(User::getFeedbacks)
                .findAny()
                .orElse(List.of());
    }

    public void postFeedback(Integer userId, Integer courseId, Feedback feedback) {
        feedback.setCourse(courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("User nor found")));
        feedback.setAuthor(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        feedbackRepository.save(feedback);
    }

    public void editFeedback(Integer userId, Feedback feedback, Integer feedbackId) {
        feedback.setId(feedbackId);
        feedback.setCourse(feedbackRepository.findById(feedbackId).orElseThrow(() -> new RuntimeException("User nor found")).getCourse());
        feedback.setAuthor(userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")));
        feedbackRepository.save(feedback);
    }

    public List<Feedback> userFeedbackForCourse(Integer userId, Integer courseId) {
        List<Feedback> userFeedbackList = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found")).getFeedbacks();
        List<Feedback> courseFeedbackList = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course Not Found")).getFeedbackList();
        List<Feedback> result  =  new ArrayList<>();
        for (Feedback feedback : courseFeedbackList) {
            if (userFeedbackList.contains(feedback)) {
                result.add(feedback);
            }
        }
        return result;
    }

}
