package com.backend.FAMS.util.Syllabus;

import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.repository.Syllabus.SyllabusRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SyllabusUtil {

    SyllabusRepository syllabusRepository;
    @Autowired
    public SyllabusUtil(SyllabusRepository syllabusRepository) {
        this.syllabusRepository = syllabusRepository;
    }

    public String generateTopicCode(String preTopicCode){
        String topicCode = preTopicCode;
        int largestSuffix = 0;
        List<Syllabus> syllabusList = syllabusRepository.findAllByTopicCodeContains(preTopicCode);
        for(Syllabus s: syllabusList){
            topicCode = s.getTopicCode();
            if(topicCode.length() >= 2){
                String suffix = topicCode.substring(topicCode.length() - 2);
                if(Integer.parseInt(suffix) > largestSuffix){
                    largestSuffix = Integer.parseInt(suffix);
                }
            }
        }

        // Generate topicCode
        largestSuffix++;
        String suffixStr = String.format("%02d", largestSuffix);
        topicCode = preTopicCode + suffixStr;
        return topicCode;
    }

    public boolean isValidExcelFile(MultipartFile file){
        return Objects.equals(file.getContentType(),  "application/vnd. openxmIformats-officedocument spreadsheetml. sheet" );
    }


 }
