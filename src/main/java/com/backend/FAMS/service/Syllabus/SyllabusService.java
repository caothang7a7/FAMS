package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public interface SyllabusService {
    Syllabus createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO, String topicCode);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName);
    List<SyllabusDTOResponse> getListSyllabus();
    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest) throws ParseException;
    SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicName);
    Syllabus exportSyllabusToExcelFile(HttpServletResponse response, String topicCode) throws IOException;
    Syllabus exportSyllabusToCSVFile(HttpServletResponse response, String topicCode) throws Exception;
    Syllabus importSyllabusFromExcel(MultipartFile file) throws IOException;
    SyllabusDTOOtherScreen showSyllabusOtherScreenByTopicCode(String topicCode);

}
