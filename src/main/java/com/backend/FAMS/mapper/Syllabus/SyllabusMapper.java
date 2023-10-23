package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface SyllabusMapper {

//    @Mapping(target = "topicOutline", ignore = true)
    SyllabusDTODetailInformation entityTodto(Syllabus syllabus);
}
