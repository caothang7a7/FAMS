package com.backend.FAMS.repository.User;


import com.backend.FAMS.entity.User.UserPermission;
import com.backend.FAMS.entity.User.user_enum.UserPermissionStatus;
import com.backend.FAMS.entity.User.user_enum.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {


    Optional<UserPermission> findByRole(UserRole roleName);

    Optional<UserPermission> findBySyllabus(UserPermissionStatus status);
}
