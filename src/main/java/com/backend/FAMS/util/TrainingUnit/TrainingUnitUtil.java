package com.backend.FAMS.util.TrainingUnit;

import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrainingUnitUtil {
    TrainingContentRepository trainingContentRepository;
    @Autowired
    public TrainingUnitUtil(TrainingContentRepository trainingContentRepository) {
        this.trainingContentRepository = trainingContentRepository;
    }

    public int generateDay(Set<TrainingUnit> trainingUnit){
        int dayNumeber = 0;
        for(TrainingUnit trainingUnit1: trainingUnit){
            for(int i = 0; i < trainingUnit.size() - 1; i++){
                    if(trainingUnit1.getDayNumber() > dayNumeber){
                        dayNumeber = trainingUnit1.getDayNumber();
                    }
            }
        }
        dayNumeber++;
        return dayNumeber;
    }

    public String generateUnitCode(Set<TrainingUnit> trainingUnits) {
        String preUnitCode = "TU-";
                String unitCode;
        int largestSuffix = 0;
        for (TrainingUnit trainingUnit : trainingUnits) {
            unitCode = trainingUnit.getUnitCode();
            String suffix = unitCode.substring(unitCode.length() - 2);
            if(Integer.parseInt(suffix) > largestSuffix){
                largestSuffix = Integer.parseInt(suffix);
            }
        }
        largestSuffix++;
        String suffixStr = String.format("%02d", largestSuffix);
        unitCode = preUnitCode + suffixStr;
        return unitCode;
    }
}
