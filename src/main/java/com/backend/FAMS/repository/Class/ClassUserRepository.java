package com.backend.FAMS.repository.Class;

import com.backend.FAMS.entity.Class.ClassUser;
import com.backend.FAMS.entity.Class.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassUserRepository extends JpaRepository<ClassUser, String> {

}
