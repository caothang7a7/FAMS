package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOtherScreen;


import java.util.List;
import java.util.Set;

public interface SyllabusService {
    List<SyllabusDTO> getListSyllabus();
    SyllabusDTOCreateOtherScreen createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName);
    SyllabusDTO createSyllabusOutlineScreen(SyllabusDTO syllabusDTO);
    SyllabusDTO createDaySyllabusOutlineScreen(SyllabusDTO syllabusDTO);
    Set<SyllabusDTO> showOutlineScreen(String topicName);
}
