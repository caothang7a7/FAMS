package com.backend.FAMS.dto.Syllabus.response;

import lombok.Data;

@Data
public class SyllabusDTOShowOtherScreen {
    private String topicName;
    private int duration;
    private String trainingPrincipal;
    private int quiz;
    private int assignment;
    private int finalTest;
    private int finalTheory;
    private int finalPractice;
    private int gpa;
}
