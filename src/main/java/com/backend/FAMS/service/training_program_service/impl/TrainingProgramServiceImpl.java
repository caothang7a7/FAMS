package com.backend.FAMS.service.training_program_service.impl;


import com.backend.FAMS.entity.training_program.TrainingProgram;
import com.backend.FAMS.entity.training_program.trainingprogram_enum.TrainingProgramStatus;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.repository.training_program_repo.TrainingProgramRepository;
import com.backend.FAMS.service.training_program_service.TrainingProgramService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TrainingProgramServiceImpl implements TrainingProgramService {

    private Path foundFile;
    private final TrainingProgramRepository trainingProgramRepository;

    @Autowired
    public TrainingProgramServiceImpl(TrainingProgramRepository trainingProgramRepository) {
        this.trainingProgramRepository = trainingProgramRepository;
    }

//    DUPLICATE TRAINING PROGRAM (AKA CLONE) by code//
//    ACTIVE AND DEACTIVATE TRAINING PROGRAM by code//
//    SEARCH TRAINING PROGRAM by name//
//    UPLOAD MATERIALS//
//    DELETE MATERIALS//
//    DOWNLOAD MATERIALS//

    //DUPLICATE TRAINING PROGRAM BY CODE AND SET STATUS TO DRAFT
    @Override
    @Transactional
    public TrainingProgram duplicateTrainingProgramById(String trainingProgramCode) {
        // Get the training program.
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramCode).orElseThrow(() -> new IllegalStateException("training program with id " + trainingProgramCode + " does not exists"));

        LocalDateTime localDateTime = LocalDateTime.now();
        String formatDate = localDateTime.toString().replace("T", " ");
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        // Duplicate the training program.
        TrainingProgram duplicatedTrainingProgram = TrainingProgram.builder()
                .trainingProgramCode(trainingProgramCode + " - " + formatDate)
                .name(trainingProgram.getName()).startTime(trainingProgram.getStartTime())
                .status(TrainingProgramStatus.DRAFT).createdBy(trainingProgram.getCreatedBy())
                .createdDate(trainingProgram.getCreatedDate()).modifiedBy(trainingProgram.getModifiedBy()).modifiedDate(out)
                .user(trainingProgram.getUser())
                .learningObjective(trainingProgram.getLearningObjective()).build();

        // Save the duplicated training program to the database.
        trainingProgramRepository.save(duplicatedTrainingProgram);

        // Return the duplicated training program.
        return duplicatedTrainingProgram;
    }

    //ACTIVATE TRAINING PROGRAM BY CODE
    @Override
    @Transactional
    public void activateTrainingProgramById(String trainingProgramCode) {
        // Get the training program.
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramCode).orElseThrow(() -> new IllegalStateException("training program with id " + trainingProgramCode + " does not exists"));

        // Set the training program to active in TrainingProgramStatus enum.
        trainingProgram.setStatus(TrainingProgramStatus.ACTIVE);

        // Save the training program to the database.
        trainingProgramRepository.save(trainingProgram);
    }

    //DEACTIVATE TRAINING PROGRAM BY CODE
    @Override
    @Transactional
    public void deactivateTrainingProgramById(String trainingProgramCode) {
        // Get the training program.
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramCode).orElseThrow(() -> new IllegalStateException("training program with id " + trainingProgramCode + " does not existss"));

        // Set the training program to inactive.
        trainingProgram.setStatus(TrainingProgramStatus.INACTIVE);

        // Save the training program to the database.
        trainingProgramRepository.save(trainingProgram);
    }

    //SEARCH TRAINING PROGRAM BY NAME
    @Override
    public List<TrainingProgram> searchTrainingProgramByName(String name) {
        // Return the list of training program that contains the name.
        List<TrainingProgram> trainingProgramList = trainingProgramRepository.findByNameContainingIgnoreCase(name);
        if (trainingProgramList.isEmpty()) {
            throw new IllegalStateException("There's no record matching with your keyword");
        } else {
            return trainingProgramList;
        }
    }


    // TEST UPLOAD MATERIALS
    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("Files-Upload");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + fileName, ioe);
        }

        return fileCode;
    }

    // TEST DOWNLOAD MATERIALS
    @Override
    public Resource getFileAsResource(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

    // TEST DELETE MATERIALS
    @Override
    public Resource deleteFile(String fileCode) throws IOException {
        Path dirPath = Paths.get("Files-Upload");

        Files.list(dirPath).forEach(file -> {
            if (file.getFileName().toString().startsWith(fileCode)) {
                foundFile = file;
            }
        });

        if (foundFile != null) {
            Files.delete(foundFile);
            return new UrlResource(foundFile.toUri());
        }

        return null;
    }

    //Export training program to excel
    @Override
    @Transactional
    public ResponseEntity<byte[]> exportTrainingProgramToExcel(String trainingProgramCode) throws IOException, Exception {
        //Get training program
        Optional<TrainingProgram> trainingProgram = trainingProgramRepository.findById(trainingProgramCode);
        //Check if training program exists
        if (trainingProgram.isEmpty()) {
            throw new NotFoundException("Training program with id " + trainingProgramCode + " does not exists");
        }
        //Get folder path and file path
        String folderPath = Objects.requireNonNull(getClass().getClassLoader().getResource("training_program_files")).getFile();
        String fileName = trainingProgramCode + ".xlsx";
        String filePath = folderPath + File.separator + fileName;
        File file = new File(filePath);
        //Check if file exists
        if (file.exists() && file.isFile()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new IllegalStateException("File with name " + fileName + " cannot be deleted");
            }
        }
        //Create workbook and sheet
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("TrainingPrograms");
            Row headerRow = sheet.createRow(0);
            String[] header = {"Training Program Code", "Name", "Start Time", "Status", "Created By", "Created Date", "Modified By", "Modified Date", "User ID", "Learning Objective Code"};
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            for (int i = 0; i < header.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(headerCellStyle);
            }
            //Create row data
            Row dataRow = sheet.createRow(1);
            dataRow.createCell(0).setCellValue(trainingProgramCode);
            dataRow.createCell(1).setCellValue(trainingProgram.get().getName());
            dataRow.createCell(2).setCellValue(trainingProgram.get().getStartTime().toString());
            dataRow.createCell(3).setCellValue(trainingProgram.get().getStatus().toString());
            dataRow.createCell(4).setCellValue(trainingProgram.get().getCreatedBy());
            dataRow.createCell(5).setCellValue(trainingProgram.get().getCreatedDate().toString());
            dataRow.createCell(6).setCellValue(trainingProgram.get().getModifiedBy());
            dataRow.createCell(7).setCellValue(trainingProgram.get().getModifiedDate() != null ? trainingProgram.get().getModifiedDate().toString() : "");
            dataRow.createCell(8).setCellValue(trainingProgram.get().getUser().getUserId());
            dataRow.createCell(9).setCellValue(trainingProgram.get().getLearningObjective().getObjectiveCode());
            //Auto size column width
            for (int i = 0; i < header.length; i++) {
                sheet.autoSizeColumn(i);
            }
            //Write to file
            FileOutputStream fileOut = new FileOutputStream(filePath);
            workbook.write(fileOut);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            //Set response
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.add("Content-Disposition", "attachment; filename=" + fileName);
            fileOut.close();
            workbook.close();
            return ResponseEntity.ok().headers(headers).contentLength(byteArrayOutputStream.size()).body(byteArrayOutputStream.toByteArray());
        } catch (IOException | IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
