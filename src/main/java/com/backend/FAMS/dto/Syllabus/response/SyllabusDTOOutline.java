package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import lombok.Data;

import javax.management.ConstructorParameters;
import java.util.Date;

@Data
public class SyllabusDTOOutline {
    private String topicCode;
    private String topicName;
    private String version;
    private SyllabusStatus syllabusStatus;
    private String createdBy;
    private String modifiedBy;
    private Date modifiedDate;
    private int assignment;
    private int quiz;
    private int finalTest;
    private int finalTheory;
    private int finalPractice;
    private int gpa;

}
