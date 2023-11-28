package com.backend.FAMS.exception;/*  Welcome to Jio word
    @author: Jio
    Date: 10/15/2023
    Time: 4:05 PM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorMessage {
    private int statusCode;
    private String message;
    private String description;
    private Date timestamp;
    private String data;


}