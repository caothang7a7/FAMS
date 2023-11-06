package com.backend.FAMS.controller.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.SyllabusOutlineScreen;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.service.sercutity.IJwtService;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import com.backend.FAMS.util.User.ValidatorUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.IOException;
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


    @PostMapping("/importExcel")
    public ResponseEntity<Syllabus> importSyllabusExcel(@RequestParam("file")MultipartFile file) throws IOException {
       return new ResponseEntity<>(syllabusService.importSyllabusFromExcel(file), HttpStatus.OK);
    }
    @GetMapping("/exportExcel/{topicCode}")
    public ResponseEntity<Syllabus> exportSyllabusExcel(HttpServletResponse response, @PathVariable("topicCode") String topicCode) throws  Exception{
        response.setContentType("application/octet-stream");

        String headerKey = "Conent-Disposition";
        String headerValue = "attachment;filename=Syllabus.xls";

        response.setHeader(headerKey, headerValue);
        return new ResponseEntity<Syllabus>(syllabusService.exportSyllabusToExcelFile(response, topicCode), HttpStatus.OK);
    }

    @GetMapping("/exportCSV/{topicCode}")
    public ResponseEntity<Syllabus> exportSyllabusCSV(HttpServletResponse response, @PathVariable("topicCode") String topicCode) throws Exception {
        try{
            response. setContentType("text/csv");
            response. setHeader("Content-Disposition", "attachment; filename=syllabus.csv");
        }catch (Exception e){

        }
        return new ResponseEntity<>(syllabusService.exportSyllabusToCSVFile(response, topicCode), HttpStatus.OK);
    }

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

    @PostMapping("/createSyllabusOtherScreen/{topicCode}")
    public ResponseEntity<Syllabus  > createSyllabusOtherScreen(@RequestBody SyllabusDTOCreateOtherScreen syllabusDTO, @PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.createSyllabusOtherScreen(syllabusDTO, topicCode), HttpStatus.CREATED);
    }

    @GetMapping("/showOtherScreen/{topicName}")
    public ResponseEntity<SyllabusDTOShowOtherScreen> showSyllabusOtherScreen(@PathVariable("topicName") String topicName) {
        return new ResponseEntity<SyllabusDTOShowOtherScreen>(syllabusService.showSyllabusOtherScreen(topicName), HttpStatus.OK);
    }
    @GetMapping("/showOtherScreenById/{topicCode}")
    public ResponseEntity<SyllabusDTOOtherScreen> showSyllabusOtherScreenByTopicCode(@PathVariable("topicCode") String topicCode) {
        return new ResponseEntity<>(syllabusService.showSyllabusOtherScreenByTopicCode(topicCode), HttpStatus.OK);
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
