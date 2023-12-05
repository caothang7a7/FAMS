package com.backend.FAMS.repository.training_content_repo;

import com.backend.FAMS.entity.training_content.TrainingContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingContentRepository extends JpaRepository<TrainingContent, String> {
    Set<TrainingContent> findByTrainingUnit_UnitCodeAndTrainingUnitDayNumberOrderByTrainingContentIdAsc(String unitCode,int day);
    TrainingContent findByTrainingUnitUnitCode(String unitCode);
    Set<TrainingContent>  findByTrainingUnit_UnitCode (String unitCode);

}
