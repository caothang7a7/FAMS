package com.backend.FAMS.Class.repository;

import com.backend.FAMS.Class.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, String> {
}
