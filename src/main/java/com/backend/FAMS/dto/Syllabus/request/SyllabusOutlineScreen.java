package com.backend.FAMS.dto.Syllabus.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SyllabusOutlineScreen {
    private String topicCode;
    private int dayNumber;
}
