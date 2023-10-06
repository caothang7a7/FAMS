package com.backend.FAMS.Syllabus.controller;

import com.backend.FAMS.Syllabus.service.servicesImpl.SysllabusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/syllabus")
public class SyllabusController {
    @Autowired
    SysllabusServiceImpl sysllabusService;

}
