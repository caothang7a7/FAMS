package com.backend.FAMS.TrainingUnit.repository;

import com.backend.FAMS.TrainingUnit.entity.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, String> {
}
