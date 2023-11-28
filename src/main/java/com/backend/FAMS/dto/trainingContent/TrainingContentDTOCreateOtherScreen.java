package com.backend.FAMS.dto.trainingContent;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingContentDTOCreateOtherScreen {
    int duration;
}
