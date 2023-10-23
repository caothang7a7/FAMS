package com.backend.FAMS.controller.Syllabus;


import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTO;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/syllabus")
public class SyllabusController {
    @Autowired
    SyllabusService syllabusService;
    @GetMapping("/list-syllabus")
    public ResponseEntity<List<SyllabusDTO>>  getAllSyllabus(){
       return new ResponseEntity<>(syllabusService.getListSyllabus(), HttpStatus.OK);
    }

    @GetMapping("/{topicCode}")
    public ResponseEntity<SyllabusDTODetailInformation> getSyllabusById(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<SyllabusDTODetailInformation>(syllabusService.getSyllabusById(topicCode), HttpStatus.OK);
    }

    @PostMapping("/createSyllabusOtherScreen")
    public ResponseEntity<SyllabusDTOCreateOtherScreen> createSyllabusOtherScreen(@RequestBody SyllabusDTOCreateOtherScreen syllabusDTO){
        return new ResponseEntity<SyllabusDTOCreateOtherScreen>(syllabusService.createSyllabusOtherScreen(syllabusDTO), HttpStatus.CREATED);
    }

    @GetMapping("/showOtherScreen/{topicName}")
    public ResponseEntity<SyllabusDTOShowOtherScreen> showSyllabusOtherScreen(@PathVariable("topicName") String topicName){
        return new ResponseEntity<SyllabusDTOShowOtherScreen>(syllabusService.showSyllabusOtherScreen(topicName), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/{topicName}")
    public ResponseEntity<Set<SyllabusDTO>> showOutlineScreen(@PathVariable("topicName") String topicName){
        return new ResponseEntity<>(syllabusService.showOutlineScreen(topicName), HttpStatus.OK);
    }
    @PostMapping("/OutlineScreen/day")
    public ResponseEntity<SyllabusDTO> createDaySyllabusOutlineScreen(@RequestBody SyllabusDTO syllabusDTO){
        return new ResponseEntity<SyllabusDTO>(syllabusService.createDaySyllabusOutlineScreen(syllabusDTO), HttpStatus.CREATED);
    }
}
