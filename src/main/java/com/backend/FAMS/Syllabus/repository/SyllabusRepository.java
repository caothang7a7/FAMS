package com.backend.FAMS.Syllabus.repository;

import com.backend.FAMS.Syllabus.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
}
