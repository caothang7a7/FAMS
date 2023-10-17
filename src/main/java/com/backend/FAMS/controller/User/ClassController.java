package com.backend.FAMS.controller.User;

import com.backend.FAMS.dto.clazz.ClassDTO;
import com.backend.FAMS.entity.Class.Classroom;
import com.backend.FAMS.service.User.ClassService.ClassService;
import com.backend.FAMS.service.User.impl.ClassServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/classroom")
public class ClassController {
    private final ClassServiceImpl classServiceimpl;

    @Autowired
    public ClassController(ClassServiceImpl classServiceimpl) {
        this.classServiceimpl = classServiceimpl;
    }

    @GetMapping
    public List<Classroom> all() {

        return classServiceimpl.all();
    }

    @GetMapping("/searchbyclasscode")
    public List<Classroom> search(@RequestParam String classcode) {

        return classServiceimpl.searchbyclasscode(classcode);
    }

    @GetMapping("/searchbyclassname")
    public List<Classroom> search2(@RequestParam String classname) {

        return classServiceimpl.searchbyclassname(classname);
    }

    @PostMapping()
    public Object createclass(@Valid @RequestBody ClassDTO classroom, BindingResult error) {
        if (error.hasErrors()) return error.getAllErrors();
        return classServiceimpl.add(classroom);
    }
    @GetMapping("/list")
    public List<Classroom> listAllClasses() {
        return classServiceimpl.getAllClasses();

    }

}
