package com.backend.FAMS.dto.Syllabus.response;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.TrainingFormat;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyllabusOutlineScreenResponse {
    private String topicCode;
    private String topicName;
    private String version;
    private List<TrainingUnit> trainingUnits;
    private String[][] unitCode;
    private String[][] unitName;
    private Integer[][] dayNumber;
    private String learningObject;
    private TrainingFormat[][] trainingFormat;
    private DeliveryType[][] deliveryType;
    private String[][] content;
    private String[][] type;
    private String[][] note;
    private Long[][] trainingContentId;
    private Integer[][] duration;


}
