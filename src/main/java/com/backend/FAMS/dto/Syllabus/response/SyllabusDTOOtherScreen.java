package com.backend.FAMS.dto.Syllabus.response;

import lombok.Data;

@Data
public class SyllabusDTOOtherScreen {
    private String topicCode;
    private String topicName;
    private String version;
    private int quiz;
    private int assignment;
    private int finalTest;
    private int finalTheory;
    private int finalPractice;
    private int gpa;
    private String trainingPrincipal;

}
