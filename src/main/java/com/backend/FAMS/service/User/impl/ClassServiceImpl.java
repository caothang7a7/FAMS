package com.backend.FAMS.service.User.impl;

import com.backend.FAMS.dto.clazz.ClassDTO;
import com.backend.FAMS.entity.Class.Classroom;

import com.backend.FAMS.entity.TrainingProgram.TrainingProgram;
import com.backend.FAMS.repository.Class.ClassRepository;
import com.backend.FAMS.repository.TrainingProgram.TrainingProgramRepository;
import com.backend.FAMS.service.User.ClassService.ClassService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassRepository classRepository;


    @Override
    public List<Classroom> all() {
        return classRepository.findAll();
    }

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
        return null;
    }
}
