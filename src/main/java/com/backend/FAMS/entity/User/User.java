package com.backend.FAMS.entity.User;

import com.backend.FAMS.entity.Class.ClassUser;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgram;
import com.backend.FAMS.entity.User.user_enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
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

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotEmpty(message = "Name is required!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Email is required!")
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Pattern(regexp = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"
            , message = "Invalid phone number!")
    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "dob", nullable = false)
    private Date dob;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "created_date", nullable = true)
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