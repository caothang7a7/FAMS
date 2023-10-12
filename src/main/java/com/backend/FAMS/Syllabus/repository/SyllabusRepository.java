package com.backend.FAMS.Syllabus.repository;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.SynthLabelUI;
import java.util.List;
import java.util.Optional;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
    @Query("select s, tcs.trainingProgram from Syllabus s JOIN s.trainingProgramSyllabusSet tcs")
    List<Syllabus> getJoinInformation();

//    @Query("SELECT s.topicName, s.topicCode, s.createdDate, s.createdBy, ttp.trainingProgramCode, ttc.duration " +
//            "FROM Syllabus s " +
//            "JOIN TrainingProgram ttp ON s.user = ttp.user " +
//            "JOIN TrainingUnit ttu ON s.topicCode = ttu.syllabus " +
//            "JOIN TrainingContent ttc ON ttu.unitCode = ttc.trainingUnit")
//    public List<SyllabusDTO> getSyllabusList();

}

