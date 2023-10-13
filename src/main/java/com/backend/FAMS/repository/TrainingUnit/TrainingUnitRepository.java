package com.backend.FAMS.repository.TrainingUnit;

import com.backend.FAMS.TrainingUnit.entity.TrainingUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingUnitRepository extends JpaRepository<TrainingUnit, String> {
    @Query("SELECT MAX(tu.dayNumber) FROM TrainingUnit tu")
    Integer findMaxDayNumber();
}
