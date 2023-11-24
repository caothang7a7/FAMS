package com.backend.FAMS.mapper.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SyllabusMapper {

    Syllabus CreateOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);
    SyllabusDTOShowOtherScreen mapToDTO(Syllabus syllabus);
    @Mapping(target = "topicCode", ignore = true)
    @Mapping(target = "technicalGroup", ignore = true)
    @Mapping(target = "version", ignore = true)
    @Mapping(target = "trainingAudience", ignore = true)
    @Mapping(target = "topicOutline", ignore = true)
    @Mapping(target = "trainingMaterial", ignore = true)
    @Mapping(target = "priority", ignore = true)
    @Mapping(target = "syllabusStatus", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    Syllabus createSyllabusOtherScreenMaptoEntity(SyllabusDTOCreateOtherScreen dto);

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
    Syllabus toEntity(SyllabusOutlineScreen syllabusOutlineScreen);


    TrainingUnit toEntity(TrainingUnitDTOCreate trainingUnitDTOCreate);
    TrainingContent toEntity(TrainingContentDTOCreateOutlineScreen trainingContentDTOCreateOutlineScreen) ;

    List<SyllabusDTOResponse> toDTO(List<Syllabus> syllabus);

    SyllabusDTOOtherScreen toDTO(Syllabus syllabus);
    SyllabusDTOShowGeneral toDtoShowGeneral(Syllabus syllabus);

    SyllabusDTOOutline toDTOOutline(Syllabus syllabus);
}