package com.backend.FAMS.repository.user_repo;


import com.backend.FAMS.entity.user.UserPermission;
import com.backend.FAMS.entity.user.user_enum.UserPermissionStatus;
import com.backend.FAMS.entity.user.user_enum.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer> {


    Optional<UserPermission> findByRole(UserRole roleName);

    Optional<UserPermission> findBySyllabus(UserPermissionStatus status);
}
