package com.backend.FAMS.controller.classroom_controller;

import com.backend.FAMS.dto.classroom_dto.ClassDTO;
import com.backend.FAMS.dto.classroom_dto.ClassroomDTO;
import com.backend.FAMS.entity.classroom.Classroom;
import com.backend.FAMS.service.classroom_service.ClassService;
import com.backend.FAMS.service.classroom_service.impl.ClassServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.ap.shaded.freemarker.core.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/v1/class", produces = "application/json;charset=UTF-8")
@RequiredArgsConstructor
public class ClassController {
    private final ClassServiceImpl classServiceimpl;
    private final ClassService iClassService;

    @GetMapping("/classCode")
    public List<Classroom> search(@RequestParam String classcode) {

        return classServiceimpl.searchbyclasscode(classcode);
    }

    @GetMapping("/className")
    public List<Classroom> search2(@RequestParam String classname) {

        return classServiceimpl.searchbyclassname(classname);
    }

    @PostMapping()
    public Object createClass(@Valid @RequestBody ClassDTO classroom, BindingResult error) {
        if (error.hasErrors()) return error.getAllErrors();
        return classServiceimpl.add(classroom);
    }
    @GetMapping("/list")
    public List<Classroom> listAllClasses() {
        return classServiceimpl.getAllClasses();

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateClass(@PathVariable Long id,
                                         @Valid @RequestBody ClassroomDTO classroomDTO,
                                         BindingResult bindingResult) throws ParseException {
        return iClassService.updateClass(id, classroomDTO, bindingResult);
//        if (bindingResult.hasErrors()){
//            return bindingResult.getAllErrors().
//        }
    }
    @PutMapping("/trainingProgram/{id}")
    public ResponseEntity<?> updateTrainingProgram(@PathVariable Long id,
                                                   @Valid @RequestBody ClassroomDTO classroomDTO,
                                                   BindingResult bindingResult) throws ParseException{
        if (bindingResult.hasErrors()){
            List<String> errors = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()){
                errors.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        return iClassService.updateTrainingProgram(id, classroomDTO);
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<?> deactiveClass(@PathVariable Long id){
        return iClassService.deactiveClass(id);
    }

}
