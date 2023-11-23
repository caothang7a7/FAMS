package com.backend.FAMS.controller.Syllabus;
import com.backend.FAMS.dto.Syllabus.request.TrainingUnitDTOCreate;
import com.backend.FAMS.dto.Syllabus.response.SyllabusOutlineScreenResponse;
import com.backend.FAMS.dto.Syllabus.response.*;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateOtherScreen;
import com.backend.FAMS.dto.ApiResponse;
import com.backend.FAMS.dto.Syllabus.request.SyllabusDTOCreateGeneralRequest;
import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.dto.trainingContent.TrainingContentDTOCreateOutlineScreen;
import com.backend.FAMS.entity.LearningObjective.learningObjective_enum.Type;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.service.Syllabus.SyllabusService;
import com.backend.FAMS.service.sercutity.IJwtService;
import com.backend.FAMS.service.sercutity.RefreshTokenService;
import com.backend.FAMS.util.User.ValidatorUtil;
import jakarta.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
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

    @GetMapping("/duplicate/{topicCode}")
    public ResponseEntity<Syllabus> duplicateSyllabus(@PathVariable("topicCode") String topicCode) throws CloneNotSupportedException {
        return new ResponseEntity<>(syllabusService.duplicateSyllabusByTopicCode(topicCode), HttpStatus.OK);
    }
    @GetMapping("/getGeneralScreen/{topicCode}")
    public ResponseEntity<SyllabusDTOShowGeneral> getGeneralScreen(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.showSyllabusGeneralByTopicCode(topicCode), HttpStatus.OK);
    }
    @GetMapping("/exportExcel/{topicCode}")
    public ResponseEntity<Syllabus> exportSyllabusExcel(HttpServletResponse response, @PathVariable("topicCode") String topicCode) throws  Exception{
        response.setContentType("application/octet-stream");

        String headerKey = "Conent-Disposition";
        String headerValue = "attachment;filename=Syllabus.xls";

        response.setHeader(headerKey, headerValue);
        return new ResponseEntity<Syllabus>(syllabusService.exportSyllabusToExcelFile(response, topicCode), HttpStatus.OK);
    }
    @PostMapping("/importExcel")
    public ResponseEntity<Syllabus> importSyllabusExcel(@RequestParam("file")MultipartFile file) throws IOException {
        return new ResponseEntity<>(syllabusService.importSyllabusFromExcel(file), HttpStatus.OK);
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
    @GetMapping("/list-syllabus/{moreElement}")
    public ResponseEntity<?> getAllSyllabus(@RequestParam(defaultValue = "1") int page, @PathVariable("moreElement") int moreElement) throws ParseException {
        ApiResponse apiResponse = new ApiResponse();
        int pageSize = 5;

        if (moreElement == moreElement) {
            if (page == 1){
                pageSize = moreElement;
            }
            else if (page > 1) {
                pageSize = moreElement;
                // Kiểm tra xem trang sau trang hiện tại có đủ 10 phần tử không
                PageRequest currentPageRequest = PageRequest.of(page - 1, pageSize);
                Page<SyllabusDTOResponse> currentPage = syllabusService.getListSyllabus(currentPageRequest);
                List<SyllabusDTOResponse> currentPageList = currentPage.getContent();

                if (currentPageList.size() < moreElement) {
                    // Trang hiện tại không đủ 10 phần tử, hiển thị tất cả phần tử có sẵn trên trang hiện tại
                    pageSize = currentPageList.size();
                }
                // Trang hiện tại có đủ 10 phần tử, nhưng kiểm tra trang tiếp theo
                PageRequest nextPageRequest = PageRequest.of(page, pageSize); // Trang tiếp theo
                Page<SyllabusDTOResponse> nextPage = syllabusService.getListSyllabus(nextPageRequest);
                List<SyllabusDTOResponse> nextPageList = nextPage.getContent();

                if (nextPageList.size() < moreElement) {
                    // Trang sau trang hiện tại không đủ 10 phần tử, cập nhật pageSize cho trang hiện tại
                    pageSize = moreElement;
                }
            }
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<SyllabusDTOResponse> syllabusDTOResponsePage = syllabusService.getListSyllabus(pageRequest);
        List<SyllabusDTOResponse> syllabusList = syllabusDTOResponsePage.getContent();
        apiResponse.ok(syllabusDTOResponsePage);
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
    }

    @GetMapping("/search-syllabus-byCreatedDate/{moreElement}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> searchSyllabusByCreatedDate(@RequestParam() Date key, @RequestParam(defaultValue = "1") int page, @PathVariable("moreElement") int moreElement){
        ApiResponse apiResponse = new ApiResponse();
        int pageSize = 5;

        if (moreElement == moreElement) {
            if (page == 1){
                pageSize = moreElement;
            }
            else if (page > 1) {
                pageSize = moreElement;
                // Kiểm tra xem trang sau trang hiện tại có đủ 10 phần tử không
                PageRequest currentPageRequest = PageRequest.of(page - 1, pageSize);
                Page<SyllabusDTOResponse> currentPage = syllabusService.searchSyllabusByCreatedDate(key, currentPageRequest);
                List<SyllabusDTOResponse> currentPageList = currentPage.getContent();

                if (currentPageList.size() < 10) {
                    // Trang hiện tại không đủ 10 phần tử, hiển thị tất cả phần tử có sẵn trên trang hiện tại
                    pageSize = currentPageList.size();
                }
                // Trang hiện tại có đủ 10 phần tử, nhưng kiểm tra trang tiếp theo
                PageRequest nextPageRequest = PageRequest.of(page, pageSize); // Trang tiếp theo
                Page<SyllabusDTOResponse> nextPage = syllabusService.searchSyllabusByCreatedDate(key, nextPageRequest);
                List<SyllabusDTOResponse> nextPageList = nextPage.getContent();

                if (nextPageList.size() < 10) {
                    // Trang sau trang hiện tại không đủ 10 phần tử, cập nhật pageSize cho trang hiện tại
                    pageSize = moreElement;
                }
            }
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<SyllabusDTOResponse> syllabusDTOResponsePage = syllabusService.searchSyllabusByCreatedDate(key, pageRequest);
        List<SyllabusDTOResponse> syllabusList = syllabusDTOResponsePage.getContent();
        apiResponse.ok(syllabusDTOResponsePage);
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
    }
    @GetMapping("/search-syllabus/{moreElement}")
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> searchSyllabus(@RequestParam() String key, @PathVariable("moreElement") int moreElement, @RequestParam(defaultValue = "1") int page){
        ApiResponse apiResponse = new ApiResponse();
        int pageSize = 5;

        if (moreElement == moreElement) {
            if (page == 1){
                pageSize = moreElement;
            }
            else if (page > 1) {
                pageSize = moreElement;
                // Kiểm tra xem trang sau trang hiện tại có đủ 10 phần tử không
                PageRequest currentPageRequest = PageRequest.of(page - 1, pageSize);
                Page<SyllabusDTOResponse> currentPage = syllabusService.searchSyllabus(key, currentPageRequest);
                List<SyllabusDTOResponse> currentPageList = currentPage.getContent();

                if (currentPageList.size() < 10) {
                    // Trang hiện tại không đủ 10 phần tử, hiển thị tất cả phần tử có sẵn trên trang hiện tại
                    pageSize = currentPageList.size();
                }
                // Trang hiện tại có đủ 10 phần tử, nhưng kiểm tra trang tiếp theo
                PageRequest nextPageRequest = PageRequest.of(page, pageSize); // Trang tiếp theo
                Page<SyllabusDTOResponse> nextPage = syllabusService.searchSyllabus(key, nextPageRequest);
                List<SyllabusDTOResponse> nextPageList = nextPage.getContent();

                if (nextPageList.size() < 10) {
                    // Trang sau trang hiện tại không đủ 10 phần tử, cập nhật pageSize cho trang hiện tại
                    pageSize = moreElement;
                }
            }
        }
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Page<SyllabusDTOResponse> syllabusDTOResponsePage = syllabusService.searchSyllabus(key, pageRequest);
        List<SyllabusDTOResponse> syllabusList = syllabusDTOResponsePage.getContent();
        apiResponse.ok(syllabusDTOResponsePage);
        return new ResponseEntity<>(syllabusList, HttpStatus.OK);
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

    @GetMapping("/showOutlineById/{topicCode}")
    public ResponseEntity<SyllabusDTOOutline> showSyllabusOutlineScreenByTopicCode(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.showSyllabusOutlineByTopicCode(topicCode), HttpStatus.OK);
    }
    @PostMapping("/createSyllabusOtherScreen/{topicCode}")
    public ResponseEntity<Syllabus  > createSyllabusOtherScreen(@RequestBody SyllabusDTOCreateOtherScreen syllabusDTO, @PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.createSyllabusOtherScreen(syllabusDTO, topicCode), HttpStatus.CREATED);
    }
    @GetMapping("/showOtherScreenById/{topicCode}")
    public ResponseEntity<SyllabusDTOOtherScreen> showSyllabusOtherScreenByTopicCode(@PathVariable("topicCode") String topicCode) {
        return new ResponseEntity<>(syllabusService.showSyllabusOtherScreenByTopicCode(topicCode), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/syllabus/{topicCode}")
    public ResponseEntity<?> showSyllabusOutlineScreen(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.showSyllabusOutlineScreen(topicCode), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/{topicCode}")
    public ResponseEntity<?> showOutlineScreen(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.showOutlineScreen(topicCode), HttpStatus.OK);
    }
    @GetMapping("/showOutlineById/{topicCode}")
    public ResponseEntity<SyllabusDTOOutline> showSyllabusOutlineScreenByTopicCode(@PathVariable("topicCode") String topicCode){
        return new ResponseEntity<>(syllabusService.showSyllabusOutlineByTopicCode(topicCode), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/{topicCode}/{day}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showeachDayinOutlineScreen(@PathVariable("topicCode") String topicCode,@PathVariable("day") int day){
        return new ResponseEntity<>(syllabusService.showeachDayinOutlineScreen(topicCode,day), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/ShowTrainingContent/{day}/{unitCode}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showtrainingContent(@PathVariable("day") int day,@PathVariable ("unitCode") String unitCode){
        return new ResponseEntity<>(syllabusService.showtrainingContentbyDayinOutlineScreen(day,unitCode), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/ShowTrainingUnit/{topicCode}/{day}")
    public ResponseEntity<SyllabusOutlineScreenResponse> showtrainingUnit(@PathVariable ("topicCode") String topicCode,@PathVariable("day") int day){
        return new ResponseEntity<>(syllabusService.showtrainingUnit(topicCode,day), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/showDeliverType")
    public ResponseEntity<?> getDeliveryTypes(){
        return new ResponseEntity<>(syllabusService.getDeliverType(), HttpStatus.OK);
    }
    @GetMapping("/OutlineScreen/showType")
    public ResponseEntity<?> getTypes(){
        return new ResponseEntity<>(syllabusService.getTypeofLearningObject(), HttpStatus.OK);
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

    @PostMapping("/OutlineScreen/createTrainingContent/{topicCode}/{dayNumber}/{unitCode}")
    public ResponseEntity<?> createTrainingContent(@PathVariable("topicCode") String topicCode,@PathVariable("dayNumber") int dayNumber,@PathVariable("unitCode") String unitCode, @RequestBody TrainingContentDTOCreateOutlineScreen dto){
        return new ResponseEntity<>(syllabusService.createTrainingContentScreen(topicCode, dayNumber,unitCode,dto), HttpStatus.OK);
    }
//    @GetMapping("/OutlineScreen/listlearningObject/{topicCode}/{type}")
//    public ResponseEntity<?> createTrainingContent(@PathVariable("topicCode") String topicCode,@PathVariable("type") Type type){
//        return new ResponseEntity<>(syllabusService.L(topicCode,type),HttpStatus.OK);
//    }
}