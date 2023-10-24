package com.backend.FAMS.service.Syllabus.impl;

import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.Syllabus.SyllabusObjectiveId;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgramSyllabus;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.LearningObjectiveMapper;
import com.backend.FAMS.mapper.Syllabus.SyllabusMapper;
import com.backend.FAMS.mapper.TrainingContent.TrainingContentMapper;
import com.backend.FAMS.mapper.User.UserMapper;
import com.backend.FAMS.repository.LearningObjective.LearningObjectiveRepository;
import com.backend.FAMS.repository.Syllabus.SyllabusObjectiveRepository;
import com.backend.FAMS.repository.Syllabus.SyllabusRepository;
import com.backend.FAMS.repository.User.UserRepository;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.repository.TrainingProgram.TrainingProgramSyllabusRepository;
import com.backend.FAMS.repository.TrainingUnit.TrainingUnitRepository;
import com.backend.FAMS.util.Syllabus.SyllabusUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SyllabusObjectiveRepository syllabusObjectiveRepository;
    @Autowired
    TrainingContentRepository trainingContentRepository;

    @Autowired
    TrainingProgramSyllabusRepository trainingProgramSyllabusRepository;
    @Autowired
    TrainingUnitRepository trainingUnitRepository;
    @Autowired
    SyllabusMapper syllabusMapper;
    @Autowired
    LearningObjectiveMapper learningObjectiveMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    TrainingContentMapper trainingContentMapper;

    @Override
    public List<SyllabusDTOResponse> getListSyllabus() {
        List<SyllabusDTOResponse> dtoList = new ArrayList<>();

        List<Syllabus> syllabusList = syllabusRepository.findAll();
        for(Syllabus syllabus: syllabusList){
            SyllabusDTOResponse dto = new SyllabusDTOResponse();
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

//    @Override
//    public List<SyllabusDTOResponse> getListSyllabus() {
//        List<SyllabusDTOResponse> mergedDTOList = new ArrayList<>();
//        List<Syllabus> syllabusList = syllabusRepository.findAll();
//        for(Syllabus syllabus: syllabusList) {
//            List<TrainingContent> trainingContentList = trainingContentRepository.findTrainingContentByTrainingUnit_UnitCode(syllabus.getTopicCode());
//            List<SyllabusDTOResponse> dto1 = syllabusMapper.toListSyllabus(syllabusList);
//            List<SyllabusDTOResponse> dto2 = trainingContentMapper.toResponse(trainingContentList);
//            for(TrainingContent trainingContent: trainingContentList) {
//                if(trainingContent.getDuration() != 0) {
//                    mergedDTOList.addAll(dto1);
//                    mergedDTOList.addAll(dto2);
//                }
//            }
//        }
//        return mergedDTOList;
//    }

    @Override
    public Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest) throws ParseException {
        Syllabus syllabus = syllabusMapper.toEntity(syllabusDTOCreateGeneralRequest);
        syllabus.setTopicName(syllabusDTOCreateGeneralRequest.getTopicName());
        syllabus.setTrainingAudience(syllabusDTOCreateGeneralRequest.getTrainingAudience());
        syllabus.setTechnicalGroup(syllabusDTOCreateGeneralRequest.getTechnicalGroup());
        syllabus.setCreatedDate(syllabusDTOCreateGeneralRequest.getCreateDate());
        User user = userRepository.findById(syllabusDTOCreateGeneralRequest.getUserID()).orElseThrow(
                () -> new NotFoundException("user not found with " +syllabusDTOCreateGeneralRequest.getUserID())
        );
        syllabus.setUser(user);


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timenow = new Date();
        Date date = dateFormat.parse(dateFormat.format(timenow));

        // Auto-generated topicCode
        String topicCode = "";
        String preTopicCode = "";
        int min = 1;
        int max = 4;
        Random random = new Random();
        int number = random.nextInt((max - min) + 1) + min;
        switch (number){
            case 1:
                preTopicCode = "A";
                break;
            case 2:
                preTopicCode = "S";
                break;
            case 3:
                preTopicCode = "K";
                break;
            case 4:
                preTopicCode = "H";
                break;
        }
        SyllabusUtil utils = new SyllabusUtil(syllabusRepository);
        topicCode = utils.generateTopicCode(preTopicCode);
        syllabus.setTopicCode(topicCode);
//        syllabusRepository.customSaveSyllabus(topicCode, syllabusDTOCreateGeneralRequest.getTopicName(), syllabusDTOCreateGeneralRequest.getTechnicalGroup(), syllabusDTOCreateGeneralRequest.getVersion(), syllabusDTOCreateGeneralRequest.getTrainingAudience(), "outline",
//                "learning material", "principles", "priority", "INACTIVE", "Quách Gia", date, syllabusDTOCreateGeneralRequest.getUserID());

        LearningObjective learningObjective1 = learningObjectiveMapper.toEntity(syllabusDTOCreateGeneralRequest);
        learningObjective1.setObjectiveCode(topicCode);
        learningObjective1.setDescription(syllabusDTOCreateGeneralRequest.getDescription());

//        learningObjectiveRepository.save(learningObjective1);

        // Tạo SyllabusObjectiveId cho quan hệ
        SyllabusObjectiveId syllabusObjectiveId = new SyllabusObjectiveId();
        syllabusObjectiveId.setTopicCode(topicCode);
        syllabusObjectiveId.setObjectiveCode(learningObjective1.getObjectiveCode());

        // Tạo một SyllabusObjective và thiết lập mối quan hệ
        SyllabusObjective syllabusObjective = new SyllabusObjective();
        syllabusObjective.setSyllabusObjectiveId(syllabusObjectiveId);
        syllabusObjective.setSyllabus(syllabus);
        syllabusObjective.setLearningObjective(learningObjective1);
//        syllabusObjectiveRepository.save(syllabusObjective);


        return syllabus;
    }

    @Override
    public SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO, String topicCode) {
        return null;
    }

    @Override
    public SyllabusDTOResponse getSyllabusById(String topicCode){
        SyllabusDTOResponse syllabusDTO = new SyllabusDTOResponse();

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
    public SyllabusDTOResponse createSyllabusOtherScreen(SyllabusDTOResponse syllabusDTO) {
        Syllabus syllabus = new Syllabus();
        TrainingContent trainingContent = new TrainingContent();

        trainingContent.setDuration(syllabusDTO.getTrainingProgramDuration());
        syllabus.setTrainingPrincipal(syllabusDTO.getTrainingPrincipal());
        syllabus.setTopicCode("A08");

        Syllabus newSyllabus = syllabusRepository.save(syllabus);

        SyllabusDTOResponse responseSyllabus = new SyllabusDTOResponse();
        responseSyllabus.setTrainingPrincipal(newSyllabus.getTrainingPrincipal());
//        responseSyllabus.setTrainingProgramDuration(syllabus.get);
        return responseSyllabus;
    }
    @Override
    public SyllabusDTOResponse createDaySyllabusOutlineScreen(SyllabusDTOResponse syllabusDTO) {
        // Get the maximum day number from the training units
//        Integer maxDayNumber = trainingUnitRepository.findMaxDayNumber();
//
//        // Calculate the new day number
//        Integer newDayNumber = maxDayNumber != null ? maxDayNumber + 1 : 1;
//
//        // Create a new TrainingUnit and set its day number
//        TrainingUnit trainingUnit = new TrainingUnit();
//        trainingUnit.setDayNumber(newDayNumber);
//
//        // Save the new TrainingUnit
//        TrainingUnit newDay = trainingUnitRepository.save(trainingUnit);
//
//        // Create a response DTO and set its properties
//        SyllabusDTO responseDay = new SyllabusDTO();
//        responseDay.setDayNumber(newDay.getDayNumber());
//
//        return responseDay;
        return null;
    }

    @Override
    public Set<SyllabusDTOResponse> showOutlineScreen(String topicName) {
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
    public SyllabusDTOResponse createSyllabusOutlineScreen(SyllabusDTOResponse syllabusDTO){
        return null;
    }
}
