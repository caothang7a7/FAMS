package com.backend.FAMS.Syllabus.controller;

import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.service.servicesImpl.SyllabusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{topicCode}")
    public ResponseEntity<SyllabusDTO> getSyllabusById(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<SyllabusDTO>(sysllabusService.getSyllabusById(topicCode), HttpStatus.OK);
    }

    @PostMapping("/createSyllabusOtherScreen")
    public ResponseEntity<SyllabusDTO> createSyllabusOtherScreen(@RequestBody SyllabusDTO syllabusDTO){
        return new ResponseEntity<SyllabusDTO>(sysllabusService.createSyllabusOtherScreen(syllabusDTO), HttpStatus.CREATED);
    }

}
