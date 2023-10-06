package com.backend.FAMS.User.entity;

import com.backend.FAMS.Class.entity.ClassUser;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import com.backend.FAMS.User.entity.user_enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "tblUser")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Name is required!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Email is required!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Password is required!")
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty(message = "Phone is required!")
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "dob", nullable = false)
    private Date DOB;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;

    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;

    // --- relationship----

    // 1-n to TrainingProgram
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<TrainingProgram> trainingPrograms;

    // 1-n to ClassUser
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ClassUser> classUsers;

    // 1-n to Syllabus
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Syllabus> syllabusSet;

    // n-1 to UserPermission
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "permission_id", nullable = false)
    @JsonIgnore
    private UserPermission userPermission;
}