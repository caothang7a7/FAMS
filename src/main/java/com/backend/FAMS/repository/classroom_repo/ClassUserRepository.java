package com.backend.FAMS.repository.classroom_repo;

import com.backend.FAMS.entity.classroom.ClassUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassUserRepository extends JpaRepository<ClassUser, String> {
}
