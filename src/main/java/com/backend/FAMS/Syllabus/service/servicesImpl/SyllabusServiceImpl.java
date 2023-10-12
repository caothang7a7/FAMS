package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.LearningObjective.entity.LearningObjective;
import com.backend.FAMS.LearningObjective.repository.LearningObjectiveRepository;
import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import com.backend.FAMS.TrainingProgram.repository.TrainingProgramRepository;
import com.backend.FAMS.User.entity.User;
import com.backend.FAMS.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.backend.FAMS.TrainingContent.repository.TrainingContentRepository;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import com.backend.FAMS.TrainingProgram.repository.TrainingProgramSyllabusRepository;



import java.util.*;

@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusRepository syllabusRepository;
    @Autowired
    TrainingProgramRepository trainingProgramRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    LearningObjectiveRepository learningObjectiveRepository;

    @Autowired
    TrainingContentRepository trainingContentRepository;

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
            dto.setOutputStandardArr(arr);
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
    public SyllabusDTO getSysllabusById(String topicCode){
        SyllabusDTO syllabusDTO = new SyllabusDTO();
        Syllabus syllabus = syllabusRepository.findById(topicCode).orElseThrow();
        LearningObjective learningObjective = learningObjectiveRepository.findById(syllabus.getTopicCode()).orElseThrow();
//        TrainingProgram trainingProgram = trainingProgramRepository.findAllBy(learningObjective.getObjectiveCode());
        User user = userRepository.findById(String.valueOf(syllabus.getUser().getUserId())).orElseThrow();
        syllabusDTO.setTopicName(syllabus.getTopicName());
        syllabusDTO.setSyllabusStatus(syllabus.getSyllabusStatus());
        syllabusDTO.setTopicCode(syllabus.getTopicCode());
        syllabusDTO.setVersion(syllabus.getVersion());
//        syllabusDTO.setTrainingProgramDuration(trainingProgram.getDuration());
        syllabusDTO.setModifiedDate(syllabus.getModifiedDate());
        syllabusDTO.setModifiedBy(syllabus.getModifiedBy());
        syllabusDTO.setUserLevel(String.valueOf(user.getUserPermission().getRole()));
        syllabusDTO.setTrainingAudience(syllabus.getTrainingAudience());
//        syllabusDTO.setOutputStandard(trainingProgram.getTrainingProgramCode());
        syllabusDTO.setTechnicalGroup(syllabus.getTechnicalGroup());
        syllabusDTO.setCourseObjective(learningObjective.getType());
        return syllabusDTO;
}
}
