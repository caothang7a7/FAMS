package com.backend.FAMS.mapper.TrainingContent;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TrainingContentMapper {
//    @Mapping(target = "content", ignore = true)
//    @Mapping(target = "type", ignore = true)
//    @Mapping(target = "note", ignore = true)
//    @Mapping(target = "learningObjective", ignore = true)
//    @Mapping(target = "trainingUnit", ignore = true)
    @Mapping(source = "TrainingContent.duration", target = "duration")
    List<SyllabusDTOResponse> toResponse(List<TrainingContent> trainingContent);

    TrainingContent toEntity(SyllabusOutlineScreen syllabusOutlineScreen);
}


