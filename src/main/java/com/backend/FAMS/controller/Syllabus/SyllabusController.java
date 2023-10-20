package com.backend.FAMS.controller.Syllabus;


import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Security.RefreshToken;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.service.sercutity.IJwtService;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import com.backend.FAMS.util.User.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/syllabus")
@RequiredArgsConstructor
public class SyllabusController {
    private final ValidatorUtil validatorUtil;
    @Autowired
    SyllabusService syllabusService;
    private final IJwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    @GetMapping("/list-syllabus")
    public ResponseEntity<List<SyllabusDTOResponse>>  getAllSyllabus(){
       return new ResponseEntity<>(syllabusService.getListSyllabus(), HttpStatus.OK);
    }
    @PostMapping("/create-general-syllabus")
    public ResponseEntity<?> createSyllabusGeneralScreen(@Valid @RequestBody SyllabusDTOCreateGeneralRequest sylaSyllabusDTOCreateGeneralRequest,
                                                         BindingResult bindingResult)
    {
        ApiResponse apiResponse = new ApiResponse();
        if(bindingResult.hasErrors()){
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        syllabusService.createSyllabusGeneralScreen(sylaSyllabusDTOCreateGeneralRequest);
        apiResponse.ok(sylaSyllabusDTOCreateGeneralRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    @GetMapping("/{topicCode}")
    public ResponseEntity<SyllabusDTOResponse> getSyllabusById(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<SyllabusDTOResponse>(syllabusService.getSyllabusById(topicCode), HttpStatus.OK);
    }

    @PostMapping("/createSyllabusOtherScreen")
    public ResponseEntity<SyllabusDTOResponse> createSyllabusOtherScreen(@RequestBody SyllabusDTOResponse syllabusDTO){
        return new ResponseEntity<SyllabusDTOResponse>(syllabusService.createSyllabusOtherScreen(syllabusDTO), HttpStatus.CREATED);
    }
    @GetMapping("/OutlineScreen/{topicName}")
    public ResponseEntity<Set<SyllabusDTOResponse>> showOutlineScreen(@PathVariable("topicName") String topicName){
        return new ResponseEntity<>(syllabusService.showOutlineScreen(topicName), HttpStatus.OK);
    }
    @PostMapping("/OutlineScreen/day")
    public ResponseEntity<SyllabusDTOResponse> createDaySyllabusOutlineScreen(@RequestBody SyllabusDTOResponse syllabusDTO){
        return new ResponseEntity<SyllabusDTOResponse>(syllabusService.createDaySyllabusOutlineScreen(syllabusDTO), HttpStatus.CREATED);
    }
}
