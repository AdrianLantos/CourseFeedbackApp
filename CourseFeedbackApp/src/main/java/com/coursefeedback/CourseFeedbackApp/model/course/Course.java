package com.coursefeedback.CourseFeedbackApp.model.course;

import com.coursefeedback.CourseFeedbackApp.model.feedback.Feedback;
import com.coursefeedback.CourseFeedbackApp.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable(name ="enrolled_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @JsonIgnore
    private List<User> usersEnrolled;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<Feedback> feedbackList;

    public Course(String name) {
        this.name = name;
    }

    public void addUser(User user){
        usersEnrolled.add(user);
    }
}
