package com.backend.FAMS.dto.trainingContent;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.TrainingFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingContentDTOCreateOutlineScreen {
    String content;
    LearningObjective learningObjective;
    int duration;
    DeliveryType deliveryType;
    TrainingFormat trainingFormat;
}
