package com.backend.FAMS.mapper.TrainingUnit;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface TrainingUnitMapper {
    TrainingUnit ToEntity(SyllabusOutlineScreen syllabusOutlineScreen);
}
