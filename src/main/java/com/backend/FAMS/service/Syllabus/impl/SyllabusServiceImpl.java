package com.backend.FAMS.service.Syllabus.impl;


import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.Syllabus.SyllabusObjectiveId;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.DeliveryType;
import com.backend.FAMS.entity.TrainingContent.trainingContent_enum.TrainingFormat;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgramSyllabus;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.mapper.Syllabus.SyllabusMapper;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.LearningObjectiveMapper;
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
import com.backend.FAMS.util.TrainingContent.TrainingContentUtil;
import com.backend.FAMS.util.TrainingUnit.TrainingUnitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;
import org.springframework.validation.BindingResult;

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
    public Page<SyllabusDTOResponse> getListSyllabus(Pageable pageable) {
        List<SyllabusDTOResponse> dtoList = new ArrayList<>();

        List<Syllabus> syllabusList = syllabusRepository.findAll();
        for (Syllabus syllabus : syllabusList) {
            SyllabusDTOResponse dto = new SyllabusDTOResponse();
            dto.setTopicCode(syllabus.getTopicCode());
            dto.setTopicName(syllabus.getTopicName());
            dto.setSyllabusStatus(syllabus.getSyllabusStatus());
            dto.setCreatedBy(syllabus.getCreatedBy());
            dto.setCreatedDate(syllabus.getCreatedDate());
            Set<TrainingContent> trainingContentList = trainingContentRepository.findByTrainingUnit_UnitCode(syllabus.getTopicCode());
            int duration = 0;
            for(TrainingContent trainingContent: trainingContentList){
                duration += trainingContent.getDuration();
                dto.setDuration(duration);
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
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), dtoList.size());

        List<SyllabusDTOResponse> pageContent = dtoList.subList(start, end);

        return new PageImpl<>(pageContent, pageable, dtoList.size());
//    return dtoList;
    }

    @Override
    public Set<SyllabusDTOResponse> searchSyllabus(String key) {
        //lỗi khi search 1 phần kí tự của field
        Set<Syllabus> syllabus = syllabusRepository.findByTopicCodeOrTopicNameContainsIgnoreCase(key, key);
        Set<SyllabusDTOResponse> dto = syllabusMapper.toDTO(syllabus);
        return dto;
    }

    @Override
    public Syllabus createSyllabusGeneralScreen(SyllabusDTOCreateGeneralRequest syllabusDTOCreateGeneralRequest, BindingResult bindingResult) throws ParseException {
        Syllabus syllabus = syllabusMapper.toEntity(syllabusDTOCreateGeneralRequest);
        Syllabus existingTopicName = syllabusRepository.findByTopicName(syllabusDTOCreateGeneralRequest.getTopicName());
        if (existingTopicName != null) {
            bindingResult.rejectValue("topicName", "duplicate.topicName", "Topic name already exists.");
        } else {
            syllabus.setTopicName(syllabusDTOCreateGeneralRequest.getTopicName());
            syllabus.setTrainingAudience(syllabusDTOCreateGeneralRequest.getTrainingAudience());
            syllabus.setTechnicalGroup(syllabusDTOCreateGeneralRequest.getTechnicalGroup());
            syllabus.setCreatedDate(syllabusDTOCreateGeneralRequest.getCreateDate());
            syllabus.setLevel(syllabusDTOCreateGeneralRequest.getLevel());
            User user = userRepository.findById(syllabusDTOCreateGeneralRequest.getUserID()).orElseThrow(
                    () -> new NotFoundException("user not found with " + syllabusDTOCreateGeneralRequest.getUserID())
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
            switch (number) {
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
            syllabusRepository.customSaveSyllabus(topicCode, syllabusDTOCreateGeneralRequest.getTopicName(), syllabusDTOCreateGeneralRequest.getTechnicalGroup(), syllabusDTOCreateGeneralRequest.getVersion(), syllabusDTOCreateGeneralRequest.getTrainingAudience(), "outline",
                    "learning material", "principles", "priority", "INACTIVE", "Quách Gia", date, syllabusDTOCreateGeneralRequest.getUserID(), 0, 0, 0, 0, 0, 0);

            LearningObjective learningObjective1 = learningObjectiveMapper.toEntity(syllabusDTOCreateGeneralRequest);
            learningObjective1.setObjectiveCode(topicCode);
            learningObjective1.setDescription(syllabusDTOCreateGeneralRequest.getDescription());
            learningObjective1.setObjectiveName(syllabusDTOCreateGeneralRequest.getLearningObjectiveName());
            learningObjective1.setType(syllabusDTOCreateGeneralRequest.getLearningObjectiveType());

            learningObjectiveRepository.save(learningObjective1);

            // Tạo SyllabusObjectiveId cho quan hệ
            SyllabusObjectiveId syllabusObjectiveId = new SyllabusObjectiveId();
            syllabusObjectiveId.setTopicCode(topicCode);
            syllabusObjectiveId.setObjectiveCode(learningObjective1.getObjectiveCode());

            // Tạo một SyllabusObjective và thiết lập mối quan hệ
            SyllabusObjective syllabusObjective = new SyllabusObjective();
            syllabusObjective.setSyllabusObjectiveId(syllabusObjectiveId);
            syllabusObjective.setSyllabus(syllabus);
            syllabusObjective.setLearningObjective(learningObjective1);
            syllabusObjectiveRepository.save(syllabusObjective);
        }
            return syllabus;
        }


    @Override
    public SyllabusDTOCreateOtherScreen createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen syllabusDTO) {
        return null;
    }

    @Override
    public SyllabusDTODetailInformation getSyllabusById(String topicCode) {
        SyllabusDTODetailInformation syllabusDTO = new SyllabusDTODetailInformation();
        Syllabus syllabus = syllabusRepository.findById(topicCode).orElseThrow();
        LearningObjective learningObjective = learningObjectiveRepository.findById(syllabus.getTopicCode()).orElseThrow();
        Set<TrainingProgramSyllabus> trainingProgram = trainingProgramRepository.findAllBySyllabus_TopicCode(syllabus.getTopicCode());
        Integer[][] integers = new Integer[trainingProgram.size()][];

        int i = 0;
        for (TrainingProgramSyllabus trainingProgramSyllabus : trainingProgram) {
            integers[i] = new Integer[]{trainingProgramSyllabus.getTrainingProgram().getDuration()};
            i++;
            syllabusDTO.setDurationArr(integers);
        }
        for (TrainingProgramSyllabus trainingProgramSyllabus : trainingProgram) {
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
    public SyllabusDTOShowOtherScreen showSyllabusOtherScreen(String topicName) {
        Syllabus syllabus = syllabusRepository.findSyllabusByTopicName(topicName);
        SyllabusDTOShowOtherScreen dtoShowOtherScreen = syllabusMapper.mapToDTO(syllabus);
        return dtoShowOtherScreen;
    }
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
    public SyllabusOutlineScreenResponse showOutlineScreen(String topicName) {
        SyllabusOutlineScreenResponse syllabusOutlineScreenResponse = new SyllabusOutlineScreenResponse();

        Syllabus syllabus = syllabusRepository.findSyllabusByTopicName(topicName);
        syllabusOutlineScreenResponse.setTopicCode(syllabus.getTopicCode());
        syllabusOutlineScreenResponse.setTopicName(syllabus.getTopicName());
        syllabusOutlineScreenResponse.setVersion(syllabus.getVersion());
        Set<TrainingUnit> trainingUnits = trainingUnitRepository.findBySyllabusTopicCodeOrderByDayNumber(syllabus.getTopicCode());
        Integer[][] integers = new Integer[trainingUnits.size()][];
        int i = 0;

        for (TrainingUnit trainingUnit : trainingUnits) {
//            syllabusOutlineScreenResponse.setUnitCode(trainingUnit.getUnitCode());
//            syllabusOutlineScreenResponse.setUnitName(trainingUnit.getUnitName());
            integers[i] = new Integer[]{trainingUnit.getDayNumber()};
            i++;
            syllabusOutlineScreenResponse.setDayNumber(integers);
            // Retrieve the day number from the training unit
            int dayNumber = trainingUnit.getDayNumber();

// Find training units by day number using the corrected parameter
//            Set<TrainingUnit> trainingUnits1 = trainingUnitRepository.findAllByDayNumber(dayNumber);
////            Set<TrainingUnit> trainingUnits1 = trainingUnitRepository.findAllByDayNumber(syllabusOutlineScreenResponse.getDayNumber());
//            for (TrainingUnit trainingUnit1: trainingUnits1){



        }

        return syllabusOutlineScreenResponse;
    }

    @Override
    public SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicCode, int day) {
        SyllabusOutlineScreenResponse dto = new SyllabusOutlineScreenResponse();
        List<TrainingUnit> trainingUnits1 = new ArrayList<>();
//        Syllabus syllabus = syllabusRepository.findSyllabusByTopicCode(topicCode);

        Set<TrainingUnit> trainingUnits = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(day,topicCode);
        String[][] strings = new String[trainingUnits.size()][];
        String[][] strings1 = new String[trainingUnits.size()][];
        Integer[][] integers = new Integer[trainingUnits.size()][];
        int i = 0;
        for (TrainingUnit trainingUnit:trainingUnits){
            strings[i] = new String[]{trainingUnit.getUnitCode()};
            strings1[i] = new String[]{trainingUnit.getUnitName()};
            integers[i] = new Integer[]{trainingUnit.getDayNumber()};
            i++;
            dto.setUnitCode(strings);
            dto.setUnitName(strings1);
            dto.setDayNumber(integers);
        }
        return dto;
    }

    @Override
    public SyllabusOutlineScreenResponse showtrainingContentbyDayinOutlineScreen(String topicCode, int day, String unitCode) {
        Syllabus syllabus = syllabusRepository.findSyllabusByTopicCode(topicCode);
        SyllabusOutlineScreenResponse dto = new SyllabusOutlineScreenResponse();
//        Set<TrainingUnit> trainingUnits = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(day,syllabus.getTopicCode());
        Set<TrainingUnit> trainingUnits = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(day,"A01");

        for (TrainingUnit trainingUnit:trainingUnits){
            Set<TrainingContent> trainingContents = trainingContentRepository.findByTrainingUnit_UnitCode(trainingUnit.getUnitCode());

            DeliveryType[][] strings = new DeliveryType[trainingContents.size()][];
            TrainingFormat[][] strings1 = new TrainingFormat[trainingContents.size()][];
            String[][] strings2 = new String[trainingContents.size()][];
            String[][] strings3 = new String[trainingContents.size()][];
            String[][] strings4 = new String[trainingContents.size()][];
            Long[][] strings5 = new Long[trainingContents.size()][];
            Integer[][] strings6 = new Integer[trainingContents.size()][];
            int y = 0;
            for (TrainingContent trainingContent:trainingContents){
                strings[y] = new DeliveryType[]{trainingContent.getDeliveryType()};
//                dto.setDeliveryType(trainingContent.getDeliveryType());
//                dto.setTrainingFormat(trainingContent.getTrainingFormat());
                strings1[y] = new TrainingFormat[]{trainingContent.getTrainingFormat()};
                strings2[y] = new String[]{trainingContent.getContent()};
                strings3[y] = new String[]{trainingContent.getType()};
                strings4[y] = new String[]{trainingContent.getNote()};
                strings5[y] = new Long[]{trainingContent.getTrainingContentId()};
                strings6[y] = new Integer[]{trainingContent.getDuration()};
                y++;
                dto.setDeliveryType(strings);
                dto.setTrainingFormat(strings1);
                dto.setContent(strings2);
                dto.setType(strings3);
                dto.setNote(strings4);
                dto.setTrainingContentId(strings5);
                dto.setDuration(strings6);
                dto.setLearningObject(trainingContent.getLearningObjective().getObjectiveCode());
            }
        }
        return dto;
    }


    @Override
    public TrainingUnit addDay(String topicCode) {
        Set<TrainingUnit> trainingUnit = trainingUnitRepository.findBySyllabusTopicCode(topicCode);
        TrainingUnitUtil util = new TrainingUnitUtil(trainingContentRepository);
        TrainingUnit trainingUnit1 = new TrainingUnit();
        String generateUnitCode = util.generateUnitCode(trainingUnit);
            int day = util.generateDay(trainingUnit);
            trainingUnit1.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
//        String unitCode = util.generateUnitCode(trainingUnit);
            trainingUnit1.setUnitCode(generateUnitCode);
            trainingUnit1.setUnitName("");
            trainingUnit1.setDayNumber(day);

            trainingUnitRepository.save(trainingUnit1);
            return trainingUnit1;

    }

    @Override
    public TrainingUnit addunitCode(int dayNumber, String topicCode) {
        Set<TrainingUnit> trainingUnit = trainingUnitRepository.findBySyllabusTopicCodeAndDayNumber(topicCode, dayNumber);
        TrainingUnitUtil util = new TrainingUnitUtil(trainingContentRepository);

        String maxUnitCode = util.getMaxUnitCode(trainingUnit);

        Set<TrainingUnit> trainingUnit1 = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCodeAndUnitCode(dayNumber, topicCode, maxUnitCode);
        for (TrainingUnit trainingUnit3 : trainingUnit1) {
            if (!trainingUnit3.getUnitName().isEmpty()) {
                // Handle the case when the existing unit is not empty
                TrainingUnit newTrainingUnit = new TrainingUnit();
                newTrainingUnit.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
                String unitCode = util.generateUnitCode(trainingUnit);
                newTrainingUnit.setUnitCode(unitCode);
                newTrainingUnit.setUnitName("");
                newTrainingUnit.setDayNumber(dayNumber);
                trainingUnitRepository.save(newTrainingUnit);
                return newTrainingUnit;
            }
        }
//        else {
//            // No existing units with the given unit code, create a new unit with "TU-01"
//
//            Set<TrainingUnit> trainingUnit1 = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(dayNumber, topicCode);
//            for (TrainingUnit trainingUnit3 : trainingUnit1) {
//                // Handle the case when the existing unit is empty
//
//                if (trainingUnit3.getUnitCode().isEmpty()) {
//                    // Add Syllabus for not null
//                    trainingUnit3.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
//                    // Fill in the unitCode and other fields
//                    trainingUnit3.setUnitCode("TU-01");
//                    // Fill in other fields here
//                    trainingUnit3.setUnitName("");
//                    trainingUnit3.setDayNumber(dayNumber);
//                    trainingUnitRepository.save(trainingUnit3);
//                    return trainingUnit3;
//                }
//            }
//        }

        // Handle the case when no units are found
        return null;
    }

    @Override
    public List<DeliveryType> getDeliverType() {
        DeliveryType[] deliveryTypes = DeliveryType.values();
        List<DeliveryType> list = new ArrayList<>();
        for(DeliveryType deliveryType: deliveryTypes){
            list.add(deliveryType);
        }
        return list;
    }

    @Override
    public TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate, String topicCode, String unitCode) {
        Set<TrainingUnit> trainingUnit = trainingUnitRepository.findBySyllabusTopicCode(topicCode);
        TrainingUnitUtil util = new TrainingUnitUtil(trainingContentRepository);
        Set<TrainingUnit> trainingUnit1 = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(dayNumber,topicCode);
            for (TrainingUnit trainingUnit3 : trainingUnit1) {
//                if (trainingUnit3.getUnitName().isEmpty() && !trainingUnit3.getUnitCode().isEmpty()) {
//                    trainingUnit3.setUnitName(trainingUnitDTOCreate.getUnitName());
//                    trainingUnitRepository.save(trainingUnit3);
//                    return trainingUnit3;
//                } else {
////                    TrainingUnit trainingUnit2 = new TrainingUnit();
//                    TrainingUnit trainingUnit2 = trainingUnitRepository.findById(unitCode).orElseThrow();
//                    trainingUnit2.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
////                    String unitCode = util.generateUnitCode(trainingUnit);
////                    trainingUnit2.setUnitCode(unitCode);
//                    trainingUnit2.setUnitName(trainingUnitDTOCreate.getUnitName());
//                    trainingUnit2.setDayNumber(dayNumber);
//                    trainingUnitRepository.save(trainingUnit2);
//                    return trainingUnit2;
//                }
                TrainingUnit trainingUnit2 = trainingUnitRepository.findById(unitCode).orElseThrow();
                trainingUnit2.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
                trainingUnit2.setUnitName(trainingUnitDTOCreate.getUnitName());
                trainingUnit2.setDayNumber(dayNumber);
                trainingUnitRepository.save(trainingUnit2);
                return trainingUnit2;
            }
            return null;
    }

    @Override
    public TrainingContent createTrainingContentScreen(int dayNumber, String unitCode,String learningObjectCode, TrainingContentDTOCreateOutlineScreen dto) {
//        TrainingContent trainingContent = syllabusMapper.toEntity(dto);
        TrainingContent trainingContent = new TrainingContent();
        trainingContent.setContent(dto.getContent());
        Set<TrainingContent> trainingContent1 = trainingContentRepository.findByTrainingUnit_UnitCode(unitCode);
        TrainingContentUtil util = new TrainingContentUtil(trainingContentRepository);
        long id = util.generateid(trainingContent1);
        trainingContent.setTrainingContentId(id);
        trainingContent.setTrainingUnit(trainingUnitRepository.findById(unitCode).orElseThrow());
        trainingContent.setLearningObjective(learningObjectiveRepository.findById(learningObjectCode).orElseThrow());
        trainingContent.setDuration(dto.getDuration());
        trainingContent.setDeliveryType(dto.getDeliveryType());
        trainingContent.setTrainingFormat(dto.getTrainingFormat());
        trainingContent.setType("ad");
        trainingContent.setNote("sad");
        trainingContentRepository.save(trainingContent);
        return trainingContent;
    }


}



