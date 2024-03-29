package com.backend.FAMS.service.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.learning_objective.learningObjective_enum.Type;
import com.backend.FAMS.entity.training_content.TrainingContent;
import com.backend.FAMS.entity.training_content.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.training_unit.TrainingUnit;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface SyllabusService {
    Syllabus createSyllabusOtherScreen(Authentication authentication ,SyllabusDTOCreateOtherScreen syllabusDTO, String topicCode);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    Page<SyllabusDTOResponse> getListSyllabus(Authentication authentication, Pageable pageable) throws ParseException;
    Syllabus createSyllabusGeneralScreen(Authentication authentication, SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest, BindingResult result) throws ParseException;
    TrainingUnit addDay(String topicCode);
    SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);
    SyllabusOutlineScreenResponse showSyllabusOutlineScreen(String topicCode);
    SyllabusOutlineScreenResponse showOutlineScreen(String topicCode);
    Syllabus exportSyllabusToExcelFile(HttpServletResponse response, String topicCode) throws IOException;
    Syllabus exportSyllabusToCSVFile(HttpServletResponse response, String topicCode) throws Exception;
    Syllabus importSyllabusFromExcel(Authentication authentication,MultipartFile file, int mode) throws IOException;
    SyllabusDTOOtherScreen showSyllabusOtherScreenByTopicCode(String topicCode);
    TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate,String topicCode,String unitCode);
    TrainingContent createTrainingContentScreen(String topicCode, int dayNumber, String unitName, TrainingContentDTOCreateOutlineScreen dto);
    SyllabusOutlineScreenResponse showtrainingContentbyDayinOutlineScreen(int day,String unitCode);
    TrainingUnit addunitCode(int dayNumber, String topicCode);
    Page<SyllabusDTOResponse> searchSyllabus(String key, Pageable pageable);
    Page<SyllabusDTOResponse> searchSyllabusByCreatedDate(String key, Pageable pageable, Date createdDate);
    SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicCode, int day);
    SyllabusDTOShowGeneral showSyllabusGeneralByTopicCode(String topicCode);

    SyllabusOutlineScreenResponse showtrainingUnit(String topicCode, int day);
    Syllabus duplicateSyllabusByTopicCode(String topicCode) throws CloneNotSupportedException;

    List<DeliveryType> getDeliverType();
    List<Type> getTypeofLearningObject();
    SyllabusDTOOutline showSyllabusOutlineByTopicCode(String topicCode);
    boolean deleteSyllabus(String topicCode);

    TrainingContent editTrainingContentScreen(String topicCode, String unitCode, Long TCcode, TrainingContentDTOCreateOutlineScreen dto);
    void deleteTrainingContentScreen(String unitCode);
    void deleteTrainingUnitScreen(String topicCode, int day, String unitCode);
}