package com.backend.FAMS.Syllabus.service;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;

import java.util.List;
import java.util.Optional;

public interface SyllabusService {
    List<SyllabusDTO> getListSyllabus();

    void createSyllabus();

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO, String topicCode);

    Optional<Syllabus> getSysllabusById(String id);

}
