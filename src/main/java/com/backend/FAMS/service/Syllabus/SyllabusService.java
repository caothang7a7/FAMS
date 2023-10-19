package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Syllabus.Syllabus;


import java.util.List;
import java.util.Set;

public interface SyllabusService {
    List<SyllabusDTOResponse> getListSyllabus();

    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest);

    SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO, String topicCode);

    SyllabusDTOResponse getSyllabusById(String topicCode);

    SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO);

    SyllabusDTOResponse createSyllabusOutlineScreen(SyllabusDTOResponse syllabusDTO);
    SyllabusDTOResponse createDaySyllabusOutlineScreen(SyllabusDTOResponse syllabusDTO);

    Set<SyllabusDTOResponse> showOutlineScreen(String topicName);
}
