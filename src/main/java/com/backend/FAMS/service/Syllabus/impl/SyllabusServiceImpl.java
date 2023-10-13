package com.backend.FAMS.service.Syllabus.impl;

import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgramSyllabus;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.repository.LearningObjective.LearningObjectiveRepository;
import com.backend.FAMS.repository.Syllabus.SyllabusRepository;
import com.backend.FAMS.repository.User.UserRepository;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.repository.TrainingProgram.TrainingProgramSyllabusRepository;
import com.backend.FAMS.repository.TrainingUnit.TrainingUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;

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
    @Autowired
    TrainingUnitRepository trainingUnitRepository;

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
        User user = userRepository.findById(Long.valueOf(String.valueOf(syllabus.getUser().getUserId()))).orElseThrow();

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
    @Override
    public SyllabusDTO createDaySyllabusOutlineScreen(SyllabusDTO syllabusDTO) {
        // Get the maximum day number from the training units
        Integer maxDayNumber = trainingUnitRepository.findMaxDayNumber();

        // Calculate the new day number
        Integer newDayNumber = maxDayNumber != null ? maxDayNumber + 1 : 1;

        // Create a new TrainingUnit and set its day number
        TrainingUnit trainingUnit = new TrainingUnit();
        trainingUnit.setDayNumber(newDayNumber);

        // Save the new TrainingUnit
        TrainingUnit newDay = trainingUnitRepository.save(trainingUnit);

        // Create a response DTO and set its properties
        SyllabusDTO responseDay = new SyllabusDTO();
        responseDay.setDayNumber(newDay.getDayNumber());

        return responseDay;
    }

    @Override
    public Set<SyllabusDTO> showOutlineScreen(String topicName) {
        return null;
    }

    //    @Override
//    public Set<SyllabusDTO> showOutlineScreen(String topicName){
//        SyllabusDTO syllabusDTO = new SyllabusDTO();
//        Syllabus syllabus = syllabusRepository.findSyllabusByTopicName(topicName);
//        Set<TrainingUnit> trainingUnitList = trainingUnitRepository.findAllById(syllabus.getTopicName());
//        for (TrainingUnit trainingUnit : trainingUnitList){
//
//        }
//
//
//        return null;
//    }
    @Override
    public SyllabusDTO createSyllabusOutlineScreen(SyllabusDTO syllabusDTO){
        return null;
    }
}