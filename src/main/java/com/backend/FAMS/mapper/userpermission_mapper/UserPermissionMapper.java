package com.backend.FAMS.mapper.userpermission_mapper;


import com.backend.FAMS.dto.user_request.UserPermissionDTOUpdateRequest;
import com.backend.FAMS.dto.user_response.UserPermissionDTOResponse;
import com.backend.FAMS.entity.user.UserPermission;
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
