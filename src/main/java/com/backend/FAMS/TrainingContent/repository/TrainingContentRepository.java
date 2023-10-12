package com.backend.FAMS.TrainingContent.repository;

import com.backend.FAMS.TrainingContent.entity.TrainingContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TrainingContentRepository extends JpaRepository<TrainingContent, String> {

Set<TrainingContent> findByTrainingUnit_UnitCode(String unitCode);


}
