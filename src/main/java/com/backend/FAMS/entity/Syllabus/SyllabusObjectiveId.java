package com.backend.FAMS.entity.Syllabus;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SyllabusObjectiveId implements Serializable {
    @Column(name = "topic_code")
    private String topicCode;

    @Column(name = "objective_code")
    private String objectiveCode;
}
