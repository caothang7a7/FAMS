package com.backend.FAMS.mapper.Syllabus;

import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;

public interface SyllabusMapper {
    Syllabus addDate(SyllabusOutlineScreen syllabusOutlineScreen);
}
