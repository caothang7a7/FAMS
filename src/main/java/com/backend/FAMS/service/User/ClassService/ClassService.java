package com.backend.FAMS.service.User.ClassService;

import com.backend.FAMS.dto.clazz.ClassDTO;
import com.backend.FAMS.entity.Class.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClassService {
    public List<Classroom> all();
    public List<Classroom> searchbyclasscode(String classcode);
    public List<Classroom> searchbyclassname(String classname);
    public Classroom add(ClassDTO classroom);
    public List<Classroom> getAllClasses();

}
