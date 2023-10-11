package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.backend.FAMS.TrainingContent.repository.TrainingContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusRepository syllabusRepository;

    @Autowired
    TrainingContentRepository trainingContentRepository;
    @Override
    public List<SyllabusDTO> getListSyllabus(String topicCode) {
        List<Syllabus> list = syllabusRepository.findAll();
        return list.stream().map(p -> mapToDto(p, topicCode)).collect(Collectors.toList());
    }
    private SyllabusDTO mapToDto(Syllabus syllabus, String topicCode){
        Syllabus s = syllabusRepository.findById(topicCode).orElseThrow();
        //Optional<TrainingProgram> trainingProgram = trainingProgramRepository.findById(s.get().getTopicCode());
        TrainingContent trainingContent = trainingContentRepository.findById(s.getTopicCode()).orElseThrow();
        SyllabusDTO dto = new SyllabusDTO();
        dto.setTopicCode(syllabus.getTopicCode());
        dto.setTopicName(syllabus.getTopicName());
        dto.setSyllabusStatus(syllabus.getSyllabusStatus());
        dto.setCreatedBy(syllabus.getCreatedBy());
        dto.setCreatedDate(syllabus.getCreatedDate());
        dto.setDuration(String.valueOf(trainingContent.getDuration()));
        return dto;
    }


    @Override
    public void createSyllabus() {

    }

    @Override
    public SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO, String topicCode) {
        return null;
    }

    @Override
    public Optional<Syllabus> getSysllabusById(String id) {
        return Optional.empty();
    }
}
