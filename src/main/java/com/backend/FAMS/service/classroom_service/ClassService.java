package com.backend.FAMS.service.classroom_service;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.classroom_dto.ClassDTO;
import com.backend.FAMS.dto.classroom_dto.ClassroomDTO;
import com.backend.FAMS.entity.classroom.Classroom;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;


public interface ClassService {
    public List<Classroom> searchbyclasscode(String classcode);
    public List<Classroom> searchbyclassname(String classname);
    public Classroom add(ClassDTO classroom);
    public List<Classroom> getAllClasses();
    ResponseEntity<ApiResponse> updateClass(Long classID , ClassroomDTO classroomDTO, BindingResult bindingResult);
    ResponseEntity<?> updateTrainingProgram(Long classID, ClassroomDTO classroomDTO);
    ResponseEntity<?> deactiveClass(Long classID);

}
