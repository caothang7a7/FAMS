package com.backend.FAMS.Syllabus.service;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;

import java.util.List;

public interface SyllabusService {
    List<Syllabus> getAll();

    void createSyllabus();

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO, String topicCode);
}
