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
import java.text.ParseException;
import java.util.List;
import java.util.Set;

public interface SyllabusService {
    SyllabusDTOCreateOtherScreen createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO);
    SyllabusDTODetailInformation getSyllabusById(String topicCode);
    SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName);
    Page<SyllabusDTOResponse> getListSyllabus(Pageable pageable);

//    SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen,String topicName);
    Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest, BindingResult result) throws ParseException;
    SyllabusOutlineScreenResponse showOutlineScreen(String topicName);
    TrainingUnit addDay(String topicCode);

    TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate,String topicCode,String unitCode);

    TrainingContent createTrainingContentScreen(int dayNumber, String unitName,String learningObjectCode, TrainingContentDTOCreateOutlineScreen dto);
    Set<SyllabusDTOResponse> searchSyllabus(String key);
    SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicName,int day);
    SyllabusOutlineScreenResponse showtrainingContentbyDayinOutlineScreen(String topicCode,int day,String unitCode);
    TrainingUnit addunitCode(int dayNumber, String topicCode);
}
