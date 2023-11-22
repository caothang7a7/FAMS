package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.LearningObjective.learningObjective_enum.Type;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.springframework.validation.BindingResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SyllabusService {
    Syllabus createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO, String topicCode);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName);
    Page<SyllabusDTOResponse> getListSyllabus(Pageable pageable) throws ParseException;
    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest, BindingResult result) throws ParseException;
    TrainingUnit addDay(String topicCode);
    SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);
    SyllabusOutlineScreenResponse showSyllabusOutlineScreen(String topicCode);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicCode);
    Syllabus exportSyllabusToExcelFile(HttpServletResponse response, String topicCode) throws IOException;
    Syllabus exportSyllabusToCSVFile(HttpServletResponse response, String topicCode) throws Exception;
    Syllabus importSyllabusFromExcel(MultipartFile file) throws IOException;
    SyllabusDTOOtherScreen showSyllabusOtherScreenByTopicCode(String topicCode);
    TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate,String topicCode,String unitCode);
    TrainingContent createTrainingContentScreen(String topicCode, int dayNumber, String unitName, TrainingContentDTOCreateOutlineScreen dto);
    SyllabusOutlineScreenResponse showtrainingContentbyDayinOutlineScreen(int day,String unitCode);
    TrainingUnit addunitCode(int dayNumber, String topicCode);
    Page<SyllabusDTOResponse> searchSyllabus(String key, Pageable pageable);
    Page<SyllabusDTOResponse> searchSyllabusByCreatedDate(Date createdDate, Pageable pageable);
    SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicCode, int day);
    SyllabusDTOShowGeneral showSyllabusGeneralByTopicCode(String topicCode);
    SyllabusOutlineScreenResponse showtrainingUnit(String topicCode, int day);

    List<DeliveryType> getDeliverType();
    List<Type> getTypeofLearningObject();
    List<LearningObjective> L(String topicCode, Type type);
}
