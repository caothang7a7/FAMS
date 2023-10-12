package com.backend.FAMS.TrainingProgram.repository;

import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {

//    @Query("SELECT t FROM TrainingProgram t " +
//            "JOIN t. tps " +
//            "JOIN tps.syllabus ts")
//    List<TrainingProgram> getTrainingProgramByTopicCode();
    List<TrainingProgram> findByTrainingProgramCode(String code);
}