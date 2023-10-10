package com.backend.FAMS.Syllabus.controller;

import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.service.servicesImpl.SyllabusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/syllabus")
public class SyllabusController {
    @Autowired
    SyllabusServiceImpl sysllabusService;

    @GetMapping
    public ResponseEntity<List<Syllabus>>  getAllSyllabus(){
       return new ResponseEntity<List<Syllabus>>(sysllabusService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Syllabus>> getSyllabusById(@PathVariable("id") String id){
        return new ResponseEntity<Optional<Syllabus>>(sysllabusService.getSysllabusById(id), HttpStatus.OK);
    }

}
