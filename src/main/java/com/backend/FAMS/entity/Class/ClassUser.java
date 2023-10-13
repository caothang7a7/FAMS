package com.backend.FAMS.entity.Class;


import com.backend.FAMS.entity.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tblClassUser")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassUser {
    @EmbeddedId
    private ClassUserId classUserId;


    @Column(name = "user_type", nullable = false)
    private String userType;

    // --- relationship----

    // n-1 to Class
    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    @JsonIgnore
    private Classroom classroom;

    // n-1 to User
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

}
