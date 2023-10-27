package com.backend.FAMS.util.TrainingContent;

import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingContentUtil {
    TrainingContentRepository trainingContentRepository;
    @Autowired
    public  TrainingContentUtil(TrainingContentRepository trainingContentRepository){
        this.trainingContentRepository = trainingContentRepository;
    }
    public long generateid(Set<TrainingContent> trainingContents){
        long id = 0;
        for(TrainingContent trainingContent: trainingContents){
            for(int i = 0; i < trainingContents.size() - 1; i++){
                if(trainingContent.getTrainingContentId() > id){
                    id = trainingContent.getTrainingContentId();
                }
            }
        }
        id++;
        return id;
    }
}
