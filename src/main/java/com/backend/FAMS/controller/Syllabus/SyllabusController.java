package com.backend.FAMS.controller.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.List;
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
    public ResponseEntity<?> createSyllabusGeneralScreen(@Valid @RequestBody SyllabusDTOCreateGeneralRequest syllaSyllabusDTOCreateGeneralRequest,
                                                         BindingResult bindingResult) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
        if(bindingResult.hasErrors()){
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        syllabusService.createSyllabusGeneralScreen(syllaSyllabusDTOCreateGeneralRequest);
        apiResponse.ok(syllaSyllabusDTOCreateGeneralRequest);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
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
    public ResponseEntity<SyllabusDTOShowOtherScreen> showSyllabusOtherScreen(@PathVariable("topicName") String topicName) {
        return new ResponseEntity<SyllabusDTOShowOtherScreen>(syllabusService.showSyllabusOtherScreen(topicName), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/{topicName}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showOutlineScreen(@PathVariable("topicName") String topicName){
        return new ResponseEntity<>(syllabusService.showOutlineScreen(topicName), HttpStatus.OK);
    }
    @PostMapping("/OutlineScreen")
    public ResponseEntity<SyllabusOutlineScreen> createDaySyllabusOutlineScreen(@RequestBody SyllabusOutlineScreen syllabusOutlineScreen) {
        return new ResponseEntity<SyllabusOutlineScreen>(syllabusService.createSyllabusOutlineScreen(syllabusOutlineScreen), HttpStatus.CREATED);
    }
}
