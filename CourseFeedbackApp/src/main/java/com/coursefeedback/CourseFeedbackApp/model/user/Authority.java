package com.coursefeedback.CourseFeedbackApp.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements GrantedAuthority {
    @Id
    private Integer id;
    @Column
    private String authority;
    @OneToMany(fetch = FetchType.EAGER)
    private List<User> users;


    public Authority(String authorityType, Integer id) {
        this.authority = authorityType;
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }
}
