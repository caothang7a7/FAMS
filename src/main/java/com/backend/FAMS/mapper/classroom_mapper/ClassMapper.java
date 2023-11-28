package com.backend.FAMS.mapper.classroom_mapper;

import com.backend.FAMS.dto.classroom_dto.ClassroomDTO;
import com.backend.FAMS.entity.classroom.Classroom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ClassMapper {
    @Mapping(target = "classId", ignore = true)
    @Mapping(target = "classCode",ignore = true)
    @Mapping(target = "createdBy",ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    void toEntity(ClassroomDTO classroomDTO, @MappingTarget Classroom classroom);
}
