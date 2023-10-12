package com.backend.FAMS.Syllabus.service.servicesImpl;

import com.backend.FAMS.LearningObjective.entity.LearningObjective;
import com.backend.FAMS.LearningObjective.repository.LearningObjectiveRepository;
import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.repository.SyllabusRepository;
import com.backend.FAMS.Syllabus.service.SyllabusService;
import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import com.backend.FAMS.TrainingProgram.entity.TrainingProgramSyllabus;
import com.backend.FAMS.TrainingProgram.repository.TrainingProgramSyllabusRepository;
import com.backend.FAMS.User.entity.User;
import com.backend.FAMS.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.backend.FAMS.TrainingContent.repository.TrainingContentRepository;

import java.util.*;

@Service
public class SyllabusServiceImpl implements SyllabusService {
    @Autowired
    SyllabusRepository syllabusRepository;
    @Autowired
    TrainingProgramSyllabusRepository trainingProgramRepository;
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
    public SyllabusDTO getSyllabusById(String topicCode){
        SyllabusDTO syllabusDTO = new SyllabusDTO();

        Syllabus syllabus = syllabusRepository.findById(topicCode).orElseThrow();
        LearningObjective learningObjective = learningObjectiveRepository.findById(syllabus.getTopicCode()).orElseThrow();
        Set<TrainingProgramSyllabus> trainingProgram = trainingProgramRepository.findAllBySyllabus_TopicCode(syllabus.getTopicCode());
        Integer[][] integers = new Integer[trainingProgram.size()][];

        int i = 0;
        for(TrainingProgramSyllabus trainingProgramSyllabus : trainingProgram){
            integers[i] = new Integer[]{trainingProgramSyllabus.getTrainingProgram().getDuration()};
            i++;
            syllabusDTO.setDurationArr(integers);
        }
        for(TrainingProgramSyllabus trainingProgramSyllabus: trainingProgram){
            syllabusDTO.setOutputStandard(trainingProgramSyllabus.getTrainingProgram().getTrainingProgramCode());
        }

//        TrainingProgram trainingProgram1 = trainingProgramRepository.findFirstBySyllabus_TopicCode(syllabus.getTopicCode()).getTrainingProgram();
        User user = userRepository.findById(String.valueOf(syllabus.getUser().getUserId())).orElseThrow();

        syllabusDTO.setTopicName(syllabus.getTopicName());
        syllabusDTO.setSyllabusStatus(syllabus.getSyllabusStatus());
        syllabusDTO.setTopicCode(syllabus.getTopicCode());
        syllabusDTO.setVersion(syllabus.getVersion());
//        syllabusDTO.setTrainingProgramDuration(trainingProgram1.getDuration());
        syllabusDTO.setModifiedDate(syllabus.getModifiedDate());
        syllabusDTO.setModifiedBy(syllabus.getModifiedBy());
        syllabusDTO.setUserLevel(String.valueOf(user.getUserPermission().getRole()));
        syllabusDTO.setTrainingAudience(syllabus.getTrainingAudience());
//        syllabusDTO.setOutputStandard(trainingProgram1.getTrainingProgramCode());
        syllabusDTO.setTechnicalGroup(syllabus.getTechnicalGroup());
        syllabusDTO.setCourseObjective(learningObjective.getType());
        return syllabusDTO;
    }

    @Override
    public SyllabusDTO createSyllabusOtherScreen(SyllabusDTO syllabusDTO) {
        Syllabus syllabus = new Syllabus();
        TrainingContent trainingContent = new TrainingContent();

        trainingContent.setDuration(syllabusDTO.getTrainingProgramDuration());
        syllabus.setTrainingPrincipal(syllabusDTO.getTrainingPrincipal());
        syllabus.setTopicCode("A08");

        Syllabus newSyllabus = syllabusRepository.save(syllabus);

        SyllabusDTO responseSyllabus = new SyllabusDTO();
        responseSyllabus.setTrainingPrincipal(newSyllabus.getTrainingPrincipal());
//        responseSyllabus.setTrainingProgramDuration(syllabus.get);
        return responseSyllabus;
    }

}
