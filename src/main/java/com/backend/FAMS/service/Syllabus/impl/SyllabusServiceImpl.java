package com.backend.FAMS.service.Syllabus.impl;
import com.backend.FAMS.dto.Syllabus.request.*;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.Syllabus.SyllabusObjective;
import com.backend.FAMS.entity.Syllabus.SyllabusObjectiveId;
import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingProgram.TrainingProgramSyllabus;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.entity.User.User;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.LearningObjectiveMapper;
import com.backend.FAMS.mapper.Syllabus.SyllabusMapper;
import com.backend.FAMS.mapper.TrainingContent.TrainingContentMapper;
import com.backend.FAMS.mapper.User.UserMapper;
import com.backend.FAMS.repository.LearningObjective.LearningObjectiveRepository;
import com.backend.FAMS.repository.Syllabus.SyllabusObjectiveRepository;
import com.backend.FAMS.repository.Syllabus.SyllabusRepository;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;
import com.backend.FAMS.repository.TrainingProgram.TrainingProgramSyllabusRepository;
import com.backend.FAMS.repository.TrainingUnit.TrainingUnitRepository;
import com.backend.FAMS.repository.User.UserRepository;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.util.Syllabus.SyllabusUtil;
import com.backend.FAMS.util.TrainingContent.TrainingContentUtil;
import com.backend.FAMS.util.TrainingUnit.TrainingUnitUtil;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
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
    public Page<SyllabusDTOResponse> getListSyllabus(Pageable pageable) throws ParseException {
        List<SyllabusDTOResponse> dtoList = new ArrayList<>();

        List<Syllabus> syllabusList = syllabusRepository.findAll();
        for (Syllabus syllabus : syllabusList) {
            SyllabusDTOResponse dto = new SyllabusDTOResponse();
            dto.setTopicCode(syllabus.getTopicCode());
            dto.setTopicName(syllabus.getTopicName());
            dto.setSyllabusStatus(syllabus.getSyllabusStatus());
            dto.setCreatedBy(syllabus.getCreatedBy());

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate = dateFormat.format(syllabus.getCreatedDate());
            dto.setCreatedDate(formattedDate);
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
    }

    @Override
    public Set<SyllabusDTOResponse> searchSyllabus(String key) {
        //lỗi khi search 1 phần kí tự của field
        Set<Syllabus> syllabus = syllabusRepository.findByTopicCodeOrTopicNameContainsIgnoreCase(key, key);
        Set<SyllabusDTOResponse> dto = syllabusMapper.toDTO(syllabus);
        return dto;}

    public Syllabus createSyllabusOtherScreen(SyllabusDTOCreateOtherScreen dto, String topicCode) {

        Syllabus syllabus = new Syllabus();
        syllabus = syllabusRepository.findSyllabusByTopicCodeContainsIgnoreCase(topicCode);

        syllabus.setTopicName(dto.getTopicName());
        syllabus.setTrainingPrincipal(dto.getTrainingPrincipal());
        syllabus.setQuiz(dto.getQuiz());
        syllabus.setAssignment(dto.getAssignment());
        syllabus.setFinalTest(dto.getFinalTest());
        syllabus.setFinalTheory(dto.getFinalTheory());
        syllabus.setFinalPractice(dto.getFinalPractice());
        syllabus.setGpa(dto.getGpa());

        Syllabus updateSyllabus = syllabusRepository.save(syllabus);

        return updateSyllabus;
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
    public SyllabusOutlineScreenResponse showeachDayinOutlineScreen(String topicName, int day) {
        SyllabusOutlineScreenResponse dto = new SyllabusOutlineScreenResponse();
        List<TrainingUnit> trainingUnits1 = new ArrayList<>();
        Syllabus syllabus = syllabusRepository.findSyllabusByTopicName(topicName);

        Set<TrainingUnit> trainingUnits = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(day,syllabus.getTopicCode());
        String[][] strings = new String[trainingUnits.size()][];
        String[][] strings1 = new String[trainingUnits.size()][];
        int i = 0;
        for (TrainingUnit trainingUnit:trainingUnits){
            strings[i] = new String[]{trainingUnit.getUnitCode()};
            strings1[i] = new String[]{trainingUnit.getUnitName()};
            i++;
            dto.setUnitCode(strings);
            dto.setUnitName(strings1);

            Set<TrainingContent> trainingContents = trainingContentRepository.findByTrainingUnit_UnitCode(trainingUnit.getUnitCode());
            String[][] strings2 = new String[trainingContents.size()][];
            int y = 0;
            for (TrainingContent trainingContent : trainingContents) {
//                syllabusOutlineScreenResponse.setDeliveryType(Enum.valueOf(DeliveryType.class,String.valueOf(trainingContent.getDeliveryType())));
                dto.setDeliveryType(trainingContent.getDeliveryType());
                dto.setTrainingFormat(trainingContent.getTrainingFormat());
                strings2[y] = new String[]{trainingContent.getContent()};
                y++;
                dto.setContent(strings2);
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

        int day = util.generateDay(trainingUnit);
        trainingUnit1.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
        String unitCode = util.generateUnitCode(trainingUnit);
        trainingUnit1.setUnitCode(unitCode);
        trainingUnit1.setUnitName("");
        trainingUnit1.setDayNumber(day);

        trainingUnitRepository.save(trainingUnit1);
        return trainingUnit1;
    }

    @Override
    public TrainingUnit createTrainingUnitScreen(int dayNumber, TrainingUnitDTOCreate trainingUnitDTOCreate, String topicCode) {
        Set<TrainingUnit> trainingUnit = trainingUnitRepository.findBySyllabusTopicCode(topicCode);
        TrainingUnitUtil util = new TrainingUnitUtil(trainingContentRepository);
        Set<TrainingUnit> trainingUnit1 = trainingUnitRepository.findTrainingUnitByDayNumberAndSyllabusTopicCode(dayNumber,topicCode);
            for (TrainingUnit trainingUnit3 : trainingUnit1) {
                if (trainingUnit3.getUnitName().isEmpty() && !trainingUnit3.getUnitCode().isEmpty()) {
                    trainingUnit3.setUnitName(trainingUnitDTOCreate.getUnitName());
                    trainingUnitRepository.save(trainingUnit3);
                    return trainingUnit3;
                } else {
                    TrainingUnit trainingUnit2 = new TrainingUnit();
                    trainingUnit2.setSyllabus(syllabusRepository.findSyllabusByTopicCode(topicCode));
                    String unitCode = util.generateUnitCode(trainingUnit);
                    trainingUnit2.setUnitCode(unitCode);
                    trainingUnit2.setUnitName(trainingUnitDTOCreate.getUnitName());
                    trainingUnit2.setDayNumber(dayNumber);
                    trainingUnitRepository.save(trainingUnit2);
                    return trainingUnit2;
                }
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


//    @Override
//    public SyllabusOutlineScreen createSyllabusOutlineScreen(SyllabusOutlineScreen syllabusOutlineScreen,String topicName){
//        Syllabus syllabus = syllabusRepository.findSyllabusByTopicName(topicName);
//        syllabus = syllabusMapper.toEntity(syllabusOutlineScreen);
//        syllabus.setTopicCode(syllabusOutlineScreen.getTopicCode());
//        syllabus.setTopicName(syllabusOutlineScreen.getTopicName());
//        syllabusRepository.save(syllabus);
//
//        Set<TrainingUnit> trainingUnit = trainingUnitRepository.findBySyllabusTopicCode(topicName);
//
//
//            TrainingContent trainingContent = trainingContentRepository.findByTrainingUnitUnitCode(trainingUnit1.getUnitCode());
//            trainingContent.getContent();
//        }
//        return syllabusOutlineScreen;
//



    public Syllabus exportSyllabusToExcelFile(HttpServletResponse response, String topicCode) throws IOException {
        Syllabus exporySyllabus = syllabusRepository.findSyllabusByTopicCodeContainsIgnoreCase(topicCode);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Syllabus Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("Topic Code");
        row.createCell(1).setCellValue("Topic Name");
        row.createCell(2).setCellValue("Technical Group");
        row.createCell(3).setCellValue("Version");
        row.createCell(4).setCellValue("Training Audience");
        row.createCell(5).setCellValue("Topic Outline");
        row.createCell(6).setCellValue("Training Material");
        row.createCell(7).setCellValue("Training Principal");
        row.createCell(8).setCellValue("Priority");
        row.createCell(9).setCellValue("Status");
        row.createCell(10).setCellValue("Created By");
        row.createCell(11).setCellValue("Created Date");
        row.createCell(12).setCellValue("Modified By");
        row.createCell(13).setCellValue("Modified Date");
        row.createCell(14).setCellValue("Quiz Percent");
        row.createCell(15).setCellValue("Assignment Percent");
        row.createCell(16).setCellValue("Final Percent");
        row.createCell(17).setCellValue("Final Theory Percent");
        row.createCell(18).setCellValue("Final Practice Percent");
        row.createCell(19).setCellValue("GPA Percent");
        row.createCell(20).setCellValue("Level");

        HSSFRow dataRow = sheet.createRow(1);
        dataRow.createCell(0).setCellValue(exporySyllabus.getTopicCode());
        dataRow.createCell(1).setCellValue(exporySyllabus.getTopicName());
        dataRow.createCell(2).setCellValue(exporySyllabus.getTechnicalGroup());
        dataRow.createCell(3).setCellValue(exporySyllabus.getVersion());
        dataRow.createCell(4).setCellValue(exporySyllabus.getTrainingAudience());
        dataRow.createCell(5).setCellValue(exporySyllabus.getTopicOutline());
        dataRow.createCell(6).setCellValue(exporySyllabus.getTrainingMaterial());
        dataRow.createCell(7).setCellValue(exporySyllabus.getTrainingPrincipal());
        dataRow.createCell(8).setCellValue(exporySyllabus.getPriority());
        dataRow.createCell(9).setCellValue(String.valueOf(exporySyllabus.getSyllabusStatus()));
        dataRow.createCell(10).setCellValue(exporySyllabus.getCreatedBy());
        dataRow.createCell(11).setCellValue(exporySyllabus.getCreatedDate());
        dataRow.createCell(12).setCellValue(exporySyllabus.getModifiedBy());
        dataRow.createCell(13).setCellValue(exporySyllabus.getModifiedDate());
        dataRow.createCell(14).setCellValue(exporySyllabus.getQuiz());
        dataRow.createCell(15).setCellValue(exporySyllabus.getAssignment());
        dataRow.createCell(16).setCellValue(exporySyllabus.getFinalTest());
        dataRow.createCell(17).setCellValue(exporySyllabus.getFinalTheory());
        dataRow.createCell(18).setCellValue(exporySyllabus.getFinalPractice());
        dataRow.createCell(19).setCellValue(exporySyllabus.getGpa());
        dataRow.createCell(20).setCellValue(String.valueOf(exporySyllabus.getLevel()));

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

        return exporySyllabus;
    }

    @Override
    public Syllabus exportSyllabusToCSVFile(HttpServletResponse response, String topicCode) throws Exception {
        ICsvBeanWriter writer = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

        String[] headings = {"Topic Code",
                "Topic Name",
                "Technical Group",
                "Version",
                "Training Audience",
                "Topic Outline",
                "Training Material",
                "Training Principal",
                "Priority",
                "Status",
                "Created By",
                "Created Date",
                "Modified By",
                "Modified Date",
                "Quiz Percent",
                "Assignment Percent",
                "Final Percent",
                "Final Theory Percent",
                "Final Practice Percent",
                "GPA Percent",
                "Level"};

        String[] data = {"topicCode",
                "topicName",
                "technicalGroup",
                "version",
                "trainingAudience",
                "topicOutline",
                "trainingMaterial",
                "trainingPrincipal",
                "priority",
                "syllabusStatus",
                "createdBy",
                "createdDate",
                "modifiedBy",
                "modifiedDate",
                "quiz",
                "assignment",
                "finalTest",
                "finalTheory",
                "finalPractice",
                "gpa",
                "level"};

        Syllabus exportSyllabus = syllabusRepository.findSyllabusByTopicCodeContainsIgnoreCase(topicCode);

        writer.writeHeader(headings);
        writer.write(exportSyllabus, data);
        writer.close();

        return exportSyllabus;
    }

    @Override
    public Syllabus importSyllabusFromExcelFile(InputStream inputStream) throws IOException {
        Syllabus syllabus = new Syllabus();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("syllabus");

            int rowIndex = 0;
            for(Row row : sheet){
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }

            }
        } catch (Exception ex) {

        }


        return syllabus;

    }
}
