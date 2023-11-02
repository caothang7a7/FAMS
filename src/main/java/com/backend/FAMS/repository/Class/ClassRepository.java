package com.backend.FAMS.repository.Class;

import com.backend.FAMS.entity.Class.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, Long> {
    @Query("Select c from Classroom c where c.classCode like %?1%")
    List<Classroom> searchbyclasscode (String Classcode);
    @Query("Select c from Classroom c where c.className like %?1%")
    List<Classroom> searchbyclassname (String Classname);
}
