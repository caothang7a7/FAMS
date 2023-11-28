package com.backend.FAMS.entity.classroom;


import com.backend.FAMS.entity.user.User;
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

    // n-1 to classroom_controller
    @ManyToOne
    @MapsId("classId")
    @JoinColumn(name = "class_id", insertable = false, updatable = false)
    @JsonIgnore
    private Classroom classroom;

    // n-1 to user_controller
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

}
