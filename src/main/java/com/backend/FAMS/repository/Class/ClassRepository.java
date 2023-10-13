package com.backend.FAMS.repository.Class;

import com.backend.FAMS.entity.Class.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Classroom, String> {

}
