package com.backend.FAMS.dto.Syllabus.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingUnitDTOCreate {
    @NotEmpty(message = "UnitCode can not be empty!")
    String unitCode;
    @NotEmpty(message = "UnitName can not be empty!")
    String unitName;
    @NotNull(message = "Date number can not be empty!")
    int dayNumber;
}
