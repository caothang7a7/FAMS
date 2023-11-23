package com.backend.FAMS.util.Syllabus;

import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusLevel;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import com.backend.FAMS.repository.Syllabus.SyllabusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SyllabusUtil {
    SyllabusRepository syllabusRepository;
    @Autowired
    public SyllabusUtil(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    public String generateTopicCode(String preTopicCode){
        String topicCode = preTopicCode;
        int largestSuffix = 0;
        List<Syllabus> syllabusList = syllabusRepository.findAllByTopicCodeContains(preTopicCode);
        for(Syllabus s: syllabusList){
            topicCode = s.getTopicCode();
            if(topicCode.length() >= 2){
                String suffix = topicCode.substring(topicCode.length() - 2);
                if(Integer.parseInt(suffix) > largestSuffix){
                    largestSuffix = Integer.parseInt(suffix);
                }
            }
        }

        // Generate topicCode
        largestSuffix++;
        String suffixStr = String.format("%02d", largestSuffix);
        topicCode = preTopicCode + suffixStr;
        return topicCode;
    }

    public boolean isValidExcelFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") ||
                contentType.equals("application/vnd.ms-excel");
    }

    public Syllabus getDataFromExcel(InputStream inputStream) throws IOException {
        Syllabus syllabus = new Syllabus();
        DataFormatter dataFormatter = new DataFormatter();
        String formattedCellStr ;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Syllabus Info");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 -> syllabus.setTopicCode(cell.getStringCellValue());
                        case 1 -> syllabus.setTopicName(cell.getStringCellValue());
                        case 2 -> syllabus.setTechnicalGroup(cell.getStringCellValue());
                        case 3 -> syllabus.setVersion(cell.getStringCellValue());
                        case 4 -> syllabus.setTrainingAudience(cell.getStringCellValue());
                        case 5 -> syllabus.setTopicOutline(cell.getStringCellValue());
                        case 6 -> syllabus.setTrainingMaterial(cell.getStringCellValue());
                        case 7 -> syllabus.setTrainingPrincipal(cell.getStringCellValue());
                        case 8 -> syllabus.setPriority(cell.getStringCellValue());
                        case 9 -> syllabus.setSyllabusStatus(SyllabusStatus.valueOf(cell.getStringCellValue()));
                        case 10 -> syllabus.setCreatedBy(cell.getStringCellValue());
                        case 11 -> syllabus.setCreatedDate(cell.getDateCellValue());
                        case 12 -> syllabus.setModifiedBy(cell.getStringCellValue());
                        case 13 -> syllabus.setModifiedDate(cell.getDateCellValue());
                        case 14 -> syllabus.setQuiz(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 15 -> syllabus.setAssignment(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 16 -> syllabus.setFinalTest(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 17 -> syllabus.setFinalTheory(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 18 -> syllabus.setFinalPractice(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 19 -> syllabus.setGpa(Integer.parseInt(dataFormatter.formatCellValue(cell)));
                        case 20 -> syllabus.setLevel(SyllabusLevel.valueOf(cell.getStringCellValue()));
                        default -> {

                        }
                    }
                    cellIndex++;
                }
            }
        } catch (Exception ex) {

        }
        return syllabus;
    }


 }
