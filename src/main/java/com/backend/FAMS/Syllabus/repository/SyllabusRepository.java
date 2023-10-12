package com.backend.FAMS.Syllabus.repository;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.SynthLabelUI;
import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {

}

