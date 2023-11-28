package com.backend.FAMS.service.home.impl;

import com.backend.FAMS.dto.api_response.ApiResponse;
import com.backend.FAMS.dto.home.CountDTO;
import com.backend.FAMS.entity.Syllabus.syllabus_enum.SyllabusStatus;
import com.backend.FAMS.entity.classroom.Classroom;
import com.backend.FAMS.entity.classroom.class_enum.ClassStatus;
import com.backend.FAMS.entity.training_program.trainingprogram_enum.TrainingProgramStatus;
import com.backend.FAMS.repository.classroom_repo.ClassRepository;
import com.backend.FAMS.repository.syllabus_repo.SyllabusRepository;
import com.backend.FAMS.repository.training_program_repo.TrainingProgramRepository;
import com.backend.FAMS.repository.user_repo.UserRepository;
import com.backend.FAMS.service.home.IHomeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SyllabusRepository syllabusRepository;

    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    public  ResponseEntity countTotal() {

        ApiResponse<CountDTO> apiResponse = new ApiResponse<>();

        CountDTO count = new CountDTO();
        long activeUserCount = userRepository.countByStatusTrue();
        long classCount = classRepository.countByStatus(ClassStatus.OPENING);
        long materialCount = syllabusRepository.countBySyllabusStatus(SyllabusStatus.ACTIVE);
        long trainingCount = trainingProgramRepository.countByStatus(TrainingProgramStatus.ACTIVE);
        count.setUser(activeUserCount);
        count.setCloss(classCount);
        count.setMaterial(materialCount);
        count.setTraining(trainingCount);

        apiResponse.ok(count);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity openClass() {
        ApiResponse apiResponse = new ApiResponse<>();
        List<Classroom> openClass = classRepository.findByStatus(ClassStatus.OPENING);
        apiResponse.ok(openClass);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
