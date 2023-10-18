package com.backend.FAMS.mapper;/*  Welcome to Jio word
=======
package com.backend.FAMS.mapper.User;
/*  Welcome to Jio word
>>>>>>> main:src/main/java/com/backend/FAMS/mapper/User/UserMapper.java
    @author: Jio
    Date: 10/6/2023
    Time: 2:46 PM

    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.User.request.UserChangePass;
import com.backend.FAMS.dto.User.request.UserDTOCreateRequest;
import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserDTOResponse;
import com.backend.FAMS.entity.User.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
//@Component
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
