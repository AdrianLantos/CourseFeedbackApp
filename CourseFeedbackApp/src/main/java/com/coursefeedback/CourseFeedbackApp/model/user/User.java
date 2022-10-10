package com.coursefeedback.CourseFeedbackApp.model.user;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "usersEnrolled")
    private List<Course> courses = new ArrayList<>();
    @OneToMany(mappedBy = "author")
    private List<Feedback> feedbacks = new ArrayList<>();

    public User (String name){
        this.name = name;
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}

