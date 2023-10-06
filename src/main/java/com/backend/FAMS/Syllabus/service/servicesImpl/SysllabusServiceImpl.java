package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SysllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusService syllabusService;
    @Autowired
    SyllabusRepository syllabusRepository;
    @Override
    public List<Syllabus> getAll() {
        return null;
    }

    @Override
    public void createSyllabus() {

    }
}
