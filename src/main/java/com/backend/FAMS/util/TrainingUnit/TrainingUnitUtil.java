package com.backend.FAMS.util.TrainingUnit;

import com.backend.FAMS.entity.TrainingUnit.TrainingUnit;
import com.backend.FAMS.repository.TrainingContent.TrainingContentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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
            for(int i = 0; i < trainingUnit.size(); i++){
                    if(trainingUnit1.getDayNumber() > dayNumeber){
                        dayNumeber = trainingUnit1.getDayNumber();
                    }
            }
        }
        dayNumeber++;
        return dayNumeber;
    }

//    public String generateUnitCode(Set<TrainingUnit> trainingUnits) {
//        String preUnitCode = "TU-";
//                String unitCode;
//        int largestSuffix = 0;
//        for (TrainingUnit trainingUnit : trainingUnits) {
//            unitCode = trainingUnit.getUnitCode();
//            String suffix = unitCode.substring(unitCode.length() - 2);
//            if(Integer.parseInt(suffix) > largestSuffix){
//                largestSuffix = Integer.parseInt(suffix);
//            }
//        }
//        largestSuffix++;
//        String suffixStr = String.format("%02d", largestSuffix);
//        unitCode = preUnitCode + suffixStr;
//        return unitCode;
//    }
public String getMaxUnitCode(List<TrainingUnit> trainingUnits) {
    String preUnitCode = "TU-";
    int largestSuffix = 0;

    for (TrainingUnit trainingUnit : trainingUnits) {
        String unitCode = trainingUnit.getUnitCode();
        if (unitCode.startsWith(preUnitCode)) {
            String suffixStr = unitCode.substring(preUnitCode.length());
            try {
                int suffix = Integer.parseInt(suffixStr);
                if (suffix > largestSuffix) {
                    largestSuffix = suffix;
                }
            } catch (NumberFormatException e) {
                // Handle invalid suffix (if any) gracefully
            }
        }
    }

    String suffixStr = String.format("%02d", largestSuffix);
    return preUnitCode + suffixStr;
}

    public String generateUnitCode(List<TrainingUnit> trainingUnits) {
        String maxUnitCode = getMaxUnitCode(trainingUnits);

        if (maxUnitCode != null) {
            int largestSuffix = Integer.parseInt(maxUnitCode.substring(3));
            largestSuffix++;
            String suffixStr = String.format("%02d", largestSuffix);
            return "TU-" + suffixStr;
        } else {
            // Handle the case when no existing unit codes are found
            return "TU-01"; // or any default value you prefer
        }
    }

}
