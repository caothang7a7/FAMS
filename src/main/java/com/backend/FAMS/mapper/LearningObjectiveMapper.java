package com.backend.FAMS.mapper;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface LearningObjectiveMapper {
    @Mapping(target = "trainingContent", ignore = true)
    @Mapping(target = "syllabusObjectives", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "objectiveName", ignore = true)
    @Mapping(target = "objectiveCode", ignore = true)
    LearningObjective toEntity(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest);
}
