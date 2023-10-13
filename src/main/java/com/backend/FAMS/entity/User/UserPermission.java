package com.backend.FAMS.entity.User;

import com.backend.FAMS.entity.User.user_enum.UserPermissionStatus;
import com.backend.FAMS.entity.User.user_enum.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tblUserPermission")

public class UserPermission {
    @Id
    @Column(name = "permission_id", nullable = false, unique = true)
    @GeneratedValue(generator = "auto_increment")
    private int permissionId;

    @Column(name = "role", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "syllabus", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermissionStatus syllabus;

    @Column(name = "training_program", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermissionStatus trainingProgram;

    @Column(name = "class", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermissionStatus closs;

    @Column(name = "learning_material", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermissionStatus learningMaterial;

    @Column(name = "user_management", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserPermissionStatus userManagement;

    // 1-n to User
    @OneToMany(mappedBy = "userPermission")
    @JsonIgnore
    private Set<User> users;
}


