package com.backend.FAMS.Syllabus.service;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;


import java.util.List;

public interface SyllabusService {
    List<SyllabusDTO> getListSyllabus();

    void createSyllabus();

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO, String topicCode);

    SyllabusDTO getSyllabusById(String topicCode);

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO);
}
