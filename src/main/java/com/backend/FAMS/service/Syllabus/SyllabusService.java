package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface SyllabusService {
    Syllabus createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO, String topicCode);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName);
    Page<SyllabusDTOResponse> getListSyllabus(Pageable pageable) throws ParseException;
    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest, BindingResult result) throws ParseException;

    TrainingUnit addDay(String topicCode);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicName);
    Syllabus exportSyllabusToExcelFile(HttpServletResponse response, String topicCode) throws IOException;
    Syllabus exportSyllabusToCSVFile(HttpServletResponse response, String topicCode) throws Exception;
    Syllabus importSyllabusFromExcel(MultipartFile file) throws IOException;

    TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate,String topicCode);

    TrainingContent createTrainingContentScreen(int dayNumber, String unitName,String learningObjectCode, TrainingContentDTOCreateOutlineScreen dto);
    Set<SyllabusDTOResponse> searchSyllabus(String key);
    SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicName, int day);
}
