package com.backend.FAMS.Syllabus.repository;

import com.backend.FAMS.Syllabus.entity.Syllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
    @Query("select s, tcs.trainingProgram from Syllabus s JOIN s.trainingProgramSyllabusSet tcs")
    public String getJoinInformation();
}
