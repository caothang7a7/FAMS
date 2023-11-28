package com.backend.FAMS.util.TrainingContent;

import com.backend.FAMS.entity.training_content.TrainingContent;
import com.backend.FAMS.repository.training_content_repo.TrainingContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingContentUtil {
    TrainingContentRepository trainingContentRepository;
    @Autowired
    public  TrainingContentUtil(TrainingContentRepository trainingContentRepository){
        this.trainingContentRepository = trainingContentRepository;
    }
    public long generateid(List<TrainingContent> trainingContents){
        long id = 0;
        for(TrainingContent trainingContent: trainingContents){
            for(int i = 0; i < trainingContents.size() ; i++){
                if(trainingContent.getTrainingContentId() > id){
                    id = trainingContent.getTrainingContentId();
                }
            }
        }
        id++;
        return id;
    }
}
