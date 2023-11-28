package com.backend.FAMS.repository.classroom_repo;

import com.backend.FAMS.entity.classroom.Classroom;
import com.backend.FAMS.entity.classroom.class_enum.ClassStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, Long> {
    @Query("Select c from Classroom c where c.classCode like %?1%")
    List<Classroom> searchbyclasscode (String Classcode);
    @Query("Select c from Classroom c where c.className like %?1%")
    List<Classroom> searchbyclassname (String Classname);
    Optional<Classroom> findByClassId(Long classID);

    long countByStatus (ClassStatus status);

    List<Classroom> findByStatus(ClassStatus status);


}
