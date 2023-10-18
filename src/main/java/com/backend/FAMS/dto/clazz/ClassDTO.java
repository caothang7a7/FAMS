package com.backend.FAMS.dto.clazz;

import com.backend.FAMS.entity.Class.class_enum.ClassStatus;
import lombok.Data;

import java.util.Date;

@Data
public class ClassDTO {
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

    private String modifiedBy;

    private Date createdDate;

    private String trainingProgramCode;

}
