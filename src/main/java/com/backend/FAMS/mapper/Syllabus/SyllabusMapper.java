package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface SyllabusMapper {

    @Mapping(target = "topicOutline", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "syllabusStatus", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "trainingPrincipal", ignore = true)
    @Mapping(target = "trainingMaterial", ignore = true)
    @Mapping(target = "user", ignore = true)
    Syllabus toEntity(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest);


//    @Mapping(target = "technicalGroup", ignore = true)
//    @Mapping(target = "version", ignore = true)
//    @Mapping(target = "trainingAudience", ignore = true)
//    @Mapping(target = "topicOutline", ignore = true)
//    @Mapping(target = "trainingPrincipal", ignore = true)
//    @Mapping(target = "modifiedBy", ignore = true)
//    @Mapping(target = "modifiedDate", ignore = true)
////    @Mapping(target = "trainingProgramDuration", ignore = true)
//    @Mapping(target = "dayNumber", ignore = true)
//    @Mapping(target = "courseObjective", ignore = true)
////    @Mapping(target = "outputStandard", ignore = true)
//    @Mapping(target = "userLevel", ignore = true)

    List<SyllabusDTOResponse> toListSyllabus(List<Syllabus> syllabus);
}
