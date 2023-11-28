package com.backend.FAMS.mapper.user_mapper;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 2:46 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.auth_dto.UserLoginDTOResponse;
import com.backend.FAMS.dto.user_request.UserChangePass;
import com.backend.FAMS.dto.user_request.UserDTOCreateRequest;
import com.backend.FAMS.dto.user_request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.user_response.UserDTOResponse;
import com.backend.FAMS.entity.user.User;
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
    @Mapping(source = "dob", target = "dob", dateFormat = "dd/MM/yyyy")
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

    UserLoginDTOResponse toLoginDTOResponse(User user);


}
