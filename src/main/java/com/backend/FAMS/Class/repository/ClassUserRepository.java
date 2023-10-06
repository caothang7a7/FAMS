package com.backend.FAMS.Class.repository;

import com.backend.FAMS.Class.entity.ClassUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassUserRepository extends JpaRepository<ClassUser, String> {
}
