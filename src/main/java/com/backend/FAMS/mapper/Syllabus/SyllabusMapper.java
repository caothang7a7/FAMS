package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;

public interface SyllabusMapper {
    Syllabus CreateOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen);

}
