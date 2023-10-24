package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import java.text.ParseException;
import java.util.List;

public interface SyllabusService {
    List<SyllabusDTOResponse> getListSyllabus();

    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest) throws ParseException;

    SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO, String topicCode);

    SyllabusDTOResponse getSyllabusById(String topicCode);

    SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO);

    SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicName);

}
