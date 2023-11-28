package com.backend.FAMS.dto.classroom_dto;

import com.backend.FAMS.entity.classroom.class_enum.ClassStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ClassroomDTO {
    private Long classId;
    private String className;
    private String classCode;
    private ClassStatus status;
    private int duration;
    private String location;
    private int fsu;
    private Date startDate;
    private Date endDate;
    private String createdBy;
    private Date createdDate;
    private String modifiedBy;
    private Date modifiedDate;
    private String trainingProgramCode;
}
