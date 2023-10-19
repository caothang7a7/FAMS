
package com.backend.FAMS.mapper.User;



import com.backend.FAMS.dto.User.request.UserChangePass;
import com.backend.FAMS.dto.User.request.UserDTOCreateRequest;
import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserDTOResponse;
import com.backend.FAMS.entity.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    // Map Entity to Response
    @Mapping(target = "userPermission", ignore = true)
    UserDTOResponse toResponse(User user);

    List<UserDTOResponse> toResponseList(List<User> userList);

    @Mapping(target = "userPermission", ignore = true)
    User toEntity(UserDTOCreateRequest userDTOCreateRequest);

    @Mapping(target = "userPermission", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "password", ignore = true)
    void toUpdate(UserDTOUpdateRequest userDTOUpdateRequest, @MappingTarget User user);


    UserChangePass toChangePasswordDTO(User user);
    User toChangePassword(UserChangePass userChangePas);


}
