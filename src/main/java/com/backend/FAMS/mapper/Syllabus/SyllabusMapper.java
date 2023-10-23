package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SyllabusMapper {

//    @Mapping(target = "topicName", ignore = false)
//    @Mapping(target = "assignment", ignore = false)
//    @Mapping(target = "quiz", ignore = false)
//    @Mapping(target = "gpa", ignore = false)
//    @Mapping(target = "trainingPrincipal", ignore = false)
//    @Mapping(target = "finalTest", ignore = false)
//    @Mapping(target = "finalPractice", ignore = false)
//    @Mapping(target = "finalTheory", ignore = false)
    SyllabusDTOShowOtherScreen mapToDTO(Syllabus syllabus);
}
