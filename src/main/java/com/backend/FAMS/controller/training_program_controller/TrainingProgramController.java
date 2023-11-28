package com.backend.FAMS.controller.training_program_controller;

import com.backend.FAMS.dto.file_upload_dto.FileUploadResponse;
import com.backend.FAMS.entity.training_program.TrainingProgram;
import com.backend.FAMS.service.training_program_service.impl.TrainingProgramServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/TrainingProgram")
public class TrainingProgramController {

    private final TrainingProgramServiceImpl trainingProgramServicesImpl;

    @Autowired
    public TrainingProgramController(TrainingProgramServiceImpl trainingProgramServicesImpl) {
        this.trainingProgramServicesImpl = trainingProgramServicesImpl;
    }

    @PostMapping("/duplicate/{id}")
    public TrainingProgram duplicateTrainingProgram(@PathVariable String id) {
        return trainingProgramServicesImpl.duplicateTrainingProgramById(id);
    }

    @PutMapping("/activate/{id}")
    public void activateTrainingProgram(@PathVariable String id) {
        trainingProgramServicesImpl.activateTrainingProgramById(id);
    }

    @PutMapping("/deactivate/{id}")
    public void deactivateTrainingProgram(@PathVariable String id) {
        trainingProgramServicesImpl.deactivateTrainingProgramById(id);
    }

    @GetMapping("/search")
    public List<TrainingProgram> searchTrainingProgram(@RequestParam String name) {
        return trainingProgramServicesImpl.searchTrainingProgramByName(name);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        long size = multipartFile.getSize();

        String filecode = TrainingProgramServiceImpl.saveFile(fileName, multipartFile);

        FileUploadResponse response = new FileUploadResponse();
        response.setFileName(fileName);
        response.setSize(size);
        response.setDeleteUri("/deleteFile/" + filecode);
        response.setDownloadUri("/downloadFile/" + filecode);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {

        Resource resource;
        try {
            resource = trainingProgramServicesImpl.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (!resource.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resource.getFilename() + "\"";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType)).header(HttpHeaders.CONTENT_DISPOSITION, headerValue).body(resource);
    }

    @DeleteMapping("/deleteFile/{fileCode}")
    public ResponseEntity<?> deleteFile(@PathVariable("fileCode") String fileCode) {

        Resource resource;
        try {
            resource = trainingProgramServicesImpl.deleteFile(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }

        if (!resource.exists()) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/export/{trainingProgramCode}")
    public ResponseEntity<?> exportTrainingProgramToExcel(@PathVariable String trainingProgramCode) throws IOException, Exception {
        try {
            return trainingProgramServicesImpl.exportTrainingProgramToExcel(trainingProgramCode);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
