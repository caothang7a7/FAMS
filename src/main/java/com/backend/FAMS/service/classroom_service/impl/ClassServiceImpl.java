package com.backend.FAMS.service.classroom_service.impl;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.classroom_dto.ClassDTO;
import com.backend.FAMS.dto.classroom_dto.ClassroomDTO;
import com.backend.FAMS.entity.classroom.Classroom;
import com.backend.FAMS.entity.classroom.class_enum.ClassStatus;
import com.backend.FAMS.entity.training_program.TrainingProgram;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.mapper.classroom_mapper.ClassMapper;
import com.backend.FAMS.repository.classroom_repo.ClassRepository;
import com.backend.FAMS.repository.training_program_repo.TrainingProgramRepository;
import com.backend.FAMS.service.classroom_service.ClassService;
import com.backend.FAMS.util.ValidatorUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableJpaAuditing
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;
    private final ClassMapper classMapper;
    private final ValidatorUtil validatorUtil;
    private final TrainingProgramRepository trainingProgramRepository;

    @Override
    public List<Classroom> searchbyclasscode (String classcode) {
        return classRepository.searchbyclasscode(classcode);
    }

    @Override
    public List<Classroom> searchbyclassname (String classname) {
        return classRepository.searchbyclassname(classname);
    }

    @Override
    public Classroom add(ClassDTO classroom) {
        Classroom c = new ModelMapper().map(classroom, Classroom.class);


        return classRepository.save(c);
    }

    @Override
    public List<Classroom> getAllClasses() {
        return classRepository.findAll();
    }
    @Override
    @Transactional
    public ResponseEntity<ApiResponse> updateClass(Long classID, ClassroomDTO classroomDTO, BindingResult bindingResult) {
        ApiResponse apiResponse = new ApiResponse();
        if (classroomDTO != null){
            Classroom classroom = classRepository.findByClassId(classID).orElseThrow(
                    () -> new NotFoundException("Update failed by not found class."));
            classMapper.toEntity(classroomDTO, classroom);
            classRepository.save(classroom);
            if (bindingResult.hasErrors()) {
                apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
                return ResponseEntity.badRequest().body(apiResponse);
            }
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> updateTrainingProgram(Long classID, ClassroomDTO classroomDTO) {
        Classroom classroom = classRepository.findByClassId(classID).orElseThrow(
                () -> new NotFoundException("Update failed by not found class.")
        );
        String trainingProgramCode = classroomDTO.getTrainingProgramCode();
        if (trainingProgramCode != null){
            Optional<TrainingProgram> trainingProgram = trainingProgramRepository.findById(trainingProgramCode);
            if (trainingProgram.isPresent()){
                classroom.setTrainingProgram(trainingProgram.get());
                classRepository.save(classroom);
                return ResponseEntity.ok().build();
            }
        } else {
            ResponseEntity.notFound().build();
        }
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<?> deactiveClass(Long classID) {
        Classroom classroom = classRepository.findByClassId(classID).orElseThrow(
                () -> new NotFoundException("Update failed by not found class.")
        );
        if (classroom != null){
            classroom.setStatus(ClassStatus.DEACTIVE);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
