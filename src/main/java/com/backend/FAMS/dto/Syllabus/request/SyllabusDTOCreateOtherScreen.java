package com.backend.FAMS.dto.Syllabus.request;

import lombok.Data;

@Data
public class SyllabusDTOCreateOtherScreen {
    private String topicName;
    private int duration;
    private String trainingPrinciples;
    private int quizPercent;
    private int assignmentPercent;
    private int finalPercent;
    private int finalTheoryPercent;
    private int finalPracticePercent;
    private int GPA;
}
