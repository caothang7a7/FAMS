package com.backend.FAMS.Syllabus.controller;

<<<<<<< HEAD

=======
import com.backend.FAMS.Syllabus.dto.SyllabusDTO;
>>>>>>> 9dcde7c32cc915f5b0e4fc31d7a7d1df0be1fe94
import com.backend.FAMS.Syllabus.entity.Syllabus;
import com.backend.FAMS.Syllabus.service.SyllabusService;
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
    SyllabusService syllabusService;
    @GetMapping
    public ResponseEntity<List<Syllabus>>  getAllSyllabus(){
       return new ResponseEntity<List<Syllabus>>(syllabusService.getListSyllabus(), HttpStatus.OK);
    }

    @GetMapping("/{topicCode}")
    public ResponseEntity<SyllabusDTO> getSyllabusById(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<SyllabusDTO>(syllabusService.getSysllabusById(topicCode), HttpStatus.OK);
    }


}
