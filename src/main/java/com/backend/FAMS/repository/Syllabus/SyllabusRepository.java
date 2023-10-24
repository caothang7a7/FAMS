package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.User.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tbl_Syllabus (topic_name, training_principal, quiz, assignment, final, final_theory, final_practice, gpa, user_id) " +
            "VALUES (:topicName, :trainingPrincipal, :quiz, :assignment, :finalTest, :finalTheory, :finalPractice, :gpa, :userId)"
            ,nativeQuery = true)
    void createSyllabusOtherScreen(@Param("topicName") String topicName,
                            @Param("trainingPrincipal") String trainingPrincipal,
                            @Param("quiz") int quiz, @Param("assignment") int assignment,
                            @Param("finalTest") int finalTest, @Param("finalTheory") int finalTheory,
                            @Param("finalPractice") int finalPractice, @Param("gpa") int gpa, @Param("userId") long userId);

    Syllabus findSyllabusByTopicName(String topicName);

}
