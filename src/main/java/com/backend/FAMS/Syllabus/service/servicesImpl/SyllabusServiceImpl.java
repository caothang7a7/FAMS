package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusRepository syllabusRepository;
    @Override
    public List<Syllabus> getAll() {
        return null;
    }

    @Override
    public void createSyllabus() {

    }

    @Override
    public SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO,String topicCode) {
        Syllabus syllabus = syllabusRepository.findById(topicCode).orElseThrow();
        return null;
    }
}
