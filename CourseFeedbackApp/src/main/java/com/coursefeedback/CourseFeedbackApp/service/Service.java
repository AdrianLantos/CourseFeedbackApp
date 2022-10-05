package com.coursefeedback.CourseFeedbackApp.service;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.coursefeedback.CourseFeedbackApp.service.AuthorityService.AuthorityMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.AuthorityService.AuthorityRepository;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.courseService.CourseRepository;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.feedbackService.FeedbackRepository;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserMemoryProvider;
import com.coursefeedback.CourseFeedbackApp.service.userService.UserRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;
    private final CourseRepository courseRepository;
    private final AuthorityRepository authorityRepository;

    public Service(FeedbackMemoryProvider feedbackMemoryProvider,
                   UserMemoryProvider memoryProvider,
                   CourseRepository courseRepository,
                   UserRepository userRepository,
                   FeedbackRepository feedbackRepository,
                   CourseMemoryProvider courseMemoryProvider,
                   AuthorityRepository authorityRepository,
                   AuthorityMemoryProvider authorityMemoryProvider) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
        this.courseRepository = courseRepository;
        this.authorityRepository = authorityRepository;

        feedbackRepository.saveAll(feedbackMemoryProvider.getFeedback());
        userRepository.saveAll(memoryProvider.getUsers());
        courseRepository.saveAll(courseMemoryProvider.getCourses());
        authorityRepository.saveAll(authorityMemoryProvider.getAuthorityList());
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
        courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not Found"))
                .getUsers()
                .add(userRepository.findById(userId)
                        .orElseThrow(() -> new RuntimeException("User not found")));
    }

    public List<Feedback> getFeedbackForCourse(Integer courseId) {
        return feedbackRepository.findAll()
                .stream()
                .filter(feedback -> courseRepository.findById(courseId).orElseThrow(()-> new RuntimeException("No matching Course"))
                        .getFeedbackList()
                        .contains(feedback.getCourse()))
                .toList();
    }

    public void deleteFeedback(Integer feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }

    public List<Course> getUserCourses(Integer userId) {
        return userRepository.findById(userId)
                .stream()
                .map(User::getCourses)
                .findAny()
                .orElse(List.of());
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
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"))
                .getFeedbacks().add(feedback);
        feedbackRepository.save(feedback);
    }

    public void editFeedback(Integer userId, Feedback newFeedback, Integer feedbackId) {
        Feedback editedFeedback = feedbackRepository.findById(feedbackId).orElseThrow(() -> new RuntimeException("Feedback does not exist"));
        if(!editedFeedback.getUser().equals(userRepository.findById(userId))){
           throw new RuntimeException("Can only edit your own feedback");
        }
        editedFeedback.setBody(newFeedback.getBody());
        editedFeedback.setTitle(newFeedback.getTitle());
        feedbackRepository.save(editedFeedback);
    }

    public void deleteUserFeedback(Integer feedbackId, Integer userId){
       if(feedbackRepository.findAll().stream()
                .filter(feedback -> feedback.getId().equals(feedbackId))
                .map(Feedback::getUser)
                .map(User::getId)
                .findAny().orElseThrow(()-> new RuntimeException("Feedback does not exist for given user"))
                .equals(userId)){
           feedbackRepository.deleteById(feedbackId);
       }
    }

}
