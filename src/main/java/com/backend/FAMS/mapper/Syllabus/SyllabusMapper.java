package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SyllabusMapper {

    Syllabus toEntity(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest);
//    @Mapping(target = "topicOutline", ignore = true)
//    @Mapping(target = "modifiedDate", ignore = true)
//    @Mapping(target = "modifiedBy", ignore = true)
//    @Mapping(target = "createdDate", ignore = true)
//    @Mapping(target = "createdBy", ignore = true)
//    @Mapping(target = "syllabusStatus", ignore = true)
//    @Mapping(target = "priority", ignore = true)
//    @Mapping(target = "trainingPrincipal", ignore = true)
//    @Mapping(target = "trainingMaterial", ignore = true)
//    @Mapping(target = "topicOutline", ignore = true)
}
