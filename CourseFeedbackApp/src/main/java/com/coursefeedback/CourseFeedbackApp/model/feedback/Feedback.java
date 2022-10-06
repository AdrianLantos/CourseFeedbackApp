package com.coursefeedback.CourseFeedbackApp.model.feedback;

import com.coursefeedback.CourseFeedbackApp.model.course.Course;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String title;
    @Column
    private String body;
    @ManyToOne(optional = false)
    @JsonIgnore
    private Course course;
    @ManyToOne(optional = false)
    @JsonIgnore
    private User user;

}
