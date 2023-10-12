package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.backend.FAMS.TrainingContent.repository.TrainingContentRepository;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgram;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import com.backend.FAMS.TrainingProgram.repository.TrainingProgramRepository;
import com.backend.FAMS.TrainingProgram.repository.TrainingProgramSyllabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusRepository syllabusRepository;

    @Autowired
    TrainingContentRepository trainingContentRepository;

    @Autowired
    TrainingProgramRepository trainingProgramRepository;
    @Autowired
    TrainingProgramSyllabusRepository trainingProgramSyllabusRepository;

    @Override
    public List<SyllabusDTO> getListSyllabus() {
        List<SyllabusDTO> dtoList = new ArrayList<>();

        List<Syllabus> syllabusList = syllabusRepository.findAll();
        for(Syllabus syllabus: syllabusList){
            SyllabusDTO dto = new SyllabusDTO();
            dto.setTopicCode(syllabus.getTopicCode());
            dto.setTopicName(syllabus.getTopicName());
            dto.setSyllabusStatus(syllabus.getSyllabusStatus());
            dto.setCreatedBy(syllabus.getCreatedBy());
            dto.setCreatedDate(syllabus.getCreatedDate());
            Set<TrainingContent> trainingContentList = trainingContentRepository.findByTrainingUnit_UnitCode(syllabus.getTopicCode());
            for(TrainingContent trainingContent: trainingContentList){
                dto.setDuration(trainingContent.getDuration());
            }
            Set<TrainingProgramSyllabus> trainingProgramSyllabi = trainingProgramSyllabusRepository.findAllBySyllabus_TopicCode(syllabus.getTopicCode());

            String[][] arr = new String[trainingProgramSyllabi.size()][];
            int i = 0;
            for (TrainingProgramSyllabus trainingProgram : trainingProgramSyllabi) {
                arr[i] = new String[]{trainingProgram.getTrainingProgram().getTrainingProgramCode()};
                i++;
            }
            dto.setOutputStandard(arr);
            dtoList.add(dto);
        }
        return dtoList;
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
