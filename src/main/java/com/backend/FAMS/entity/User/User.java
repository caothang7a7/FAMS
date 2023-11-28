package com.backend.FAMS.entity.user;

import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.classroom.ClassUser;
import com.backend.FAMS.entity.refreshtoken.RefreshToken;
import com.backend.FAMS.entity.training_program.TrainingProgram;
import com.backend.FAMS.entity.user.user_enum.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tblUser")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;


    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

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

    @Version
    @Column(name = "modified_version", nullable = true)
    private  Integer version = 0;

    // --- relationship----

    // 1-n to file_upload_dto
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<TrainingProgram> trainingPrograms;

    // 1-n to ClassUser
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<ClassUser> classUsers;

    // 1-n to syllabus
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private Set<Syllabus> syllabusSet;

    // n-1 to UserPermission
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "permission_id", nullable = false)
    @JsonIgnore
    private UserPermission userPermission;


    @OneToOne
    RefreshToken refreshToken;

    // auth_controller
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String permission : userPermission.getPermissions()) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}