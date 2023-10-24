package com.backend.FAMS.dto.Syllabus.request;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.TrainingFormat;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyllabusOutlineScreen {
    private String topicCode;
    private String topicName;
    private String unitCode;
    private String unitName;
    private Integer[][] dayNumber;
    private int duration;
    private DeliveryType deliveryType;
    private TrainingFormat trainingFormat;
    private LearningObjective learningObjective;

    public void setDuration(int duration) {
        if (duration >= 0 && duration <= 480) {
            this.duration = duration;
        } else {
            throw new IllegalArgumentException("Duration must be between 0 and 480 minutes.");
        }
    }
}