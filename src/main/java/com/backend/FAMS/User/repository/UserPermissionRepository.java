package com.backend.FAMS.User.repository;

import com.backend.FAMS.User.entity.UserPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, String> {
}
