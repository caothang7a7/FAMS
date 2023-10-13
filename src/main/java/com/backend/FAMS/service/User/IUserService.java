package com.backend.FAMS.service.User;/*  Welcome to Jio word
    @author: Jio
    Date: 10/6/2023
    Time: 2:44 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/


import com.backend.FAMS.dto.User.request.UserDTOUpdateRequest;
import com.backend.FAMS.dto.User.response.UserDTOResponse;
import com.backend.FAMS.entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    Page<User> findAll(Pageable pageable);

    User createUser(User user, String roleName);

    ResponseEntity<UserDTOResponse> updateUser(UserDTOUpdateRequest userDTOUpdateRequest,
                                               Long id, String roleName, BindingResult bindingResult);

    ResponseEntity<UserDTOResponse> getUser(Long id);
}
