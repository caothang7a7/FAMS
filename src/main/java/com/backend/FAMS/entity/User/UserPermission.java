package com.backend.FAMS.entity.user;

import com.backend.FAMS.entity.user.user_enum.UserPermissionStatus;
import com.backend.FAMS.entity.user.user_enum.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
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

    // 1-n to user_controller
    @OneToMany(mappedBy = "userPermission")
    @JsonIgnore
    private Set<User> users;


    // method
    public Set<String> getPermissions() {
        Set<String> permissions = new HashSet<>();
        permissions.add(role.name());
        setPremission(syllabus, permissions,"SYLLABUS");
        setPremission(trainingProgram, permissions,"TRAINING PROGRAM");
        setPremission(closs, permissions, "CLASS");
        setPremission(learningMaterial, permissions, "LEARNING MATERIAL" );
        setPremission(userManagement, permissions, "USER MANAGEMENT");

        for (String obj : permissions) {
            System.out.println(obj);
        }

        return permissions;
    }

    private void setPremission(UserPermissionStatus premission, Set<String> permissions,String permissionName) {
        if (premission == UserPermissionStatus.FULL_ACCESS) {


            permissions.add("VIEW_" + permissionName);
            permissions.add("MODIFYS_" + permissionName);
            permissions.add("CREATE_" + permissionName);
            // ... thêm các quyền khác tương ứng với trạng thái FULL_ACCESS
        }else if(premission ==  UserPermissionStatus.ACCESS_DENIED){
            permissions.add(premission + "_"+permissionName);
        }
        else {
            permissions.add("VIEW_" + permissionName);
            permissions.add(premission + "_"+permissionName);
        }
    }

    // -------------


}


