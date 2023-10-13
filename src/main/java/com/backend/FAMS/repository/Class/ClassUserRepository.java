package com.backend.FAMS.repository.Class;

import com.backend.FAMS.entity.Class.ClassUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassUserRepository extends JpaRepository<ClassUser, String> {
}
