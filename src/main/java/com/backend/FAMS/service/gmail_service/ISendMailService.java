package com.backend.FAMS.service.gmail_service;/*  Welcome to Jio word
    @author: Jio
    Date: 10/27/2023
    Time: 2:57 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.entity.user.User;

public interface ISendMailService {
    void sendMailCreatedUser(User user, String password);
}
