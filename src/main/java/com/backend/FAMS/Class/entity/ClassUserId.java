package com.backend.FAMS.Class.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class ClassUserId implements Serializable {
    @Column(name = "class_id")
    private Long classId;

    @Column(name = "user_id")
    private Long userId;
}
