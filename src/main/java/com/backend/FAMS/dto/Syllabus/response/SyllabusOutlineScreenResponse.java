package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.TrainingFormat;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyllabusOutlineScreenResponse {
    private String topicCode;
    private String topicName;
    private String unitCode;
    private String unitName;
    private Integer[][] dayNumber;
    private String learningObject;
    private TrainingFormat trainingFormat;
    private DeliveryType deliveryType;
    private String[][] content;


}
