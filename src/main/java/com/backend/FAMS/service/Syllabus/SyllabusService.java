package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;


import java.util.List;
import java.util.Set;

public interface SyllabusService {
    List<SyllabusDTO> getListSyllabus();

    void createSyllabus();

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO, String topicCode);

    SyllabusDTO getSyllabusById(String topicCode);

    SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO);

    SyllabusDTO createSyllabusOutlineScreen(SyllabusDTO syllabusDTO);
    SyllabusDTO createDaySyllabusOutlineScreen(SyllabusDTO syllabusDTO);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicName);
}
