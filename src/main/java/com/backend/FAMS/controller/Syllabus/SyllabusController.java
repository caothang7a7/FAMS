package com.backend.FAMS.controller.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTODetailInformation;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.service.sercutity.IJwtService;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import com.backend.FAMS.util.User.ValidatorUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/syllabus")
@RequiredArgsConstructor
public class SyllabusController {
    private final ValidatorUtil validatorUtil;
    @Autowired
    SyllabusService syllabusService;
    private final IJwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @ControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
            ApiResponse apiResponse = new ApiResponse();
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("message", ex.getMessage());
            apiResponse.error(errorMap);
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/list-syllabus")
    public ResponseEntity<?>  getAllSyllabus(@RequestParam(defaultValue = "1") int page){
       ApiResponse apiResponse = new ApiResponse();
        PageRequest pageRequest = PageRequest.of(page - 1, 4);
        Page<SyllabusDTOResponse> syllabusDTOResponsePage = syllabusService.getListSyllabus(pageRequest);
        List<SyllabusDTOResponse> syllabusList = syllabusDTOResponsePage.getContent();
        apiResponse.ok(syllabusDTOResponsePage);
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
    }

    @GetMapping("/search-syllabus/{key}")
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> searchSyllabus(@PathVariable("key") String key){
        return new ResponseEntity<>(syllabusService.searchSyllabus(key), HttpStatus.OK);
    }

    @PostMapping("/create-general-syllabus")
    public ResponseEntity<?> createSyllabusGeneralScreen(@Valid @RequestBody SyllabusDTOCreateGeneralRequest syllaSyllabusDTOCreateGeneralRequest,
                                                         BindingResult bindingResult) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
        Syllabus syllabus = syllabusService.createSyllabusGeneralScreen(syllaSyllabusDTOCreateGeneralRequest, bindingResult);
        if(bindingResult.hasErrors()){
            apiResponse.error(validatorUtil.handleValidationErrors(bindingResult.getFieldErrors()));
            return ResponseEntity.badRequest().body(apiResponse);
        }
        apiResponse.ok(syllaSyllabusDTOCreateGeneralRequest);
        return new ResponseEntity<>(syllabus, HttpStatus.OK);
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
    @GetMapping("/OutlineScreen/{topicCode}/{day}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showeachDayinOutlineScreen(@PathVariable("topicCode") String topicCode,@PathVariable("day") int day){
        return new ResponseEntity<>(syllabusService.showeachDayinOutlineScreen(topicCode,day), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/{topicCode}/{day}/{unitCode}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showtrainingContent(@PathVariable("topicCode") String topicCode,@PathVariable("day") int day,@PathVariable ("unitCode") String unitCode){
        return new ResponseEntity<>(syllabusService.showtrainingContentbyDayinOutlineScreen(topicCode,day,unitCode), HttpStatus.OK);
    }

    @GetMapping("/OutlineScreen/showDeliverType")
    public ResponseEntity<?> getDeliveryTypes(){
        return new ResponseEntity<>(syllabusService.getDeliverType(), HttpStatus.OK);
    }

    @PostMapping("/OutlineScreen/addDay/{topicName}")
    public ResponseEntity<?> addOutlineScreen(@PathVariable("topicName") String topicName){
        return new ResponseEntity<>(syllabusService.addDay(topicName), HttpStatus.OK);
    }

    @PostMapping("/OutlineScreen/createTrainingUnit/{topicCode}/{dayNumber}/{unitCode}")
    public ResponseEntity<?> createTrainingUnit(@PathVariable("dayNumber") int dayNumber, @RequestBody TrainingUnitDTOCreate dto,@PathVariable("topicCode") String topicCode,@PathVariable("unitCode") String unitCode){
        return new ResponseEntity<>(syllabusService.createTrainingUnitScreen(dayNumber, dto,topicCode,unitCode), HttpStatus.OK);
    }

    @PostMapping("/OutlineScreen/addTrainingUnitCode/{topicCode}/{dayNumber}")
    public ResponseEntity<?> addTrainingUnit(@PathVariable("topicCode") String topicCode,@PathVariable("dayNumber") int dayNumber){
        return new ResponseEntity<>(syllabusService.addunitCode(dayNumber,topicCode), HttpStatus.OK);
    }

    @PostMapping("/OutlineScreen/createTrainingContent/{dayNumber}/{unitCode}/{learningObjectCode}")
    public ResponseEntity<?> createTrainingContent(@PathVariable("dayNumber") int dayNumber,@PathVariable("unitCode") String unitCode,@PathVariable("learningObjectCode") String learingObjectCode, @RequestBody TrainingContentDTOCreateOutlineScreen dto){
        return new ResponseEntity<>(syllabusService.createTrainingContentScreen(dayNumber,unitCode,learingObjectCode,dto), HttpStatus.OK);
    }
}
