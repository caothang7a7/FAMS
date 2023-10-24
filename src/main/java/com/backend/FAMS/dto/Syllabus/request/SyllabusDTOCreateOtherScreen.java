package com.backend.FAMS.dto.Syllabus.request;

import lombok.Data;

@Data
public class SyllabusDTOCreateOtherScreen {
    private String topicName;
    private String trainingPrincipal;
    private int quiz;
    private int assignment;
    private int finalTest;
    private int finalTheory;
    private int finalPractice;
    private int gpa;
    private long userId;
}
