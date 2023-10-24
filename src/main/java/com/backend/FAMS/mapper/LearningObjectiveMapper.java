package com.backend.FAMS.mapper;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LearningObjectiveMapper {
    LearningObjective toEntity(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest);
}
