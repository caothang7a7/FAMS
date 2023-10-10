package com.backend.FAMS.Syllabus.repository;

import com.backend.FAMS.Syllabus.entity.SyllabusObjective;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  SyllabusObjectiveRepository extends JpaRepository<SyllabusObjective, String> {
}
