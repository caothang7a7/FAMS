package com.backend.FAMS.mapper.Syllabus;


import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.mapstruct.Mapping;

public interface SyllabusMapper {
    Syllabus CreateOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);

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
}
