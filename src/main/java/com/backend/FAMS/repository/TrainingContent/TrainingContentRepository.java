package com.backend.FAMS.repository.TrainingContent;

import com.backend.FAMS.entity.TrainingContent.TrainingContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TrainingContentRepository extends JpaRepository<TrainingContent, String> {
    Set<TrainingContent> findByTrainingUnit_UnitCode(String unitCode);
}
