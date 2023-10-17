package com.backend.FAMS.mapper;


import com.backend.FAMS.dto.User.request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserPermissionDTOResponse;
import com.backend.FAMS.entity.User.UserPermission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserPermissionMapper {
    @Mapping(target = "role", ignore = true)
    UserPermissionDTOResponse toResponse(UserPermission userPermission);

    List<UserPermissionDTOResponse> toResponseList(List<UserPermission> userPermissionList);

    @Mapping(target = "role", ignore = true)
    UserPermission toEntity(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest);

    @Mapping(target = "role", ignore = true)
    void toUpdate(UserPermissionDTOUpdateRequest userPermissionDTOUpdateRequest, @MappingTarget UserPermission userPermission);
}
