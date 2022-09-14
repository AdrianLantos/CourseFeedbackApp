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
    @Column
    private String username;
    @Column
    private String password;
    @ManyToMany
    private List<Course> courses = new ArrayList<>();
    @OneToMany
    private List<Feedback> feedbacks = new ArrayList<>();
    @ManyToOne
    private Authority authority;

    public User (String name, String username, String password){
        this.username = username;
        this.name = name;
        this.password = password;
    }

}

