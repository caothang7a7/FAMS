package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.entity.LearningObjective.LearningObjective;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    Syllabus findSyllabusByTopicCodeContainsIgnoreCase(String topicCode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tbl_Syllabus (topic_code, topic_name, technical_group, version, training_audience," +
            " topic_outline, training_material, training_principal, priority," +
            " public_status, created_by, created_date, user_id, assignment, final, final_theory, final_practice, gpa, quiz, level) " +
            "VALUES (:topicCode, :topicName, :technicalGroup, :version, :trainingAudience,"+
            ":topicOutline, :trainingMaterial, :trainingPrincipal, :priority, :publicStatus,"+
            ":createdBy, :createdDate, :userId, :assignment, :finalTest, :finalTheory, :finalPractice, :gpa, :quiz, :level)"
            ,nativeQuery = true)
    void customSaveSyllabus(@Param("topicCode") String topicCode, @Param("topicName") String topicName, @Param("technicalGroup") String technicalGroup,
                            @Param("version") String version, @Param("trainingAudience") String trainingAudience, @Param("topicOutline") String topicOutline,
                            @Param("trainingMaterial") String trainingMaterial, @Param("trainingPrincipal") String trainingPrincipal,
                            @Param("priority") String priority, @Param("publicStatus") String publicStatus,
                            @Param("createdBy") String createdBy, @Param("createdDate") Date createdDate,
                            @Param("userId") Long userId, @Param("assignment") int assignment, @Param("finalTest") int finalTest,
                            @Param("finalTheory") int finalTheory, @Param("finalPractice") int finalPractice, @Param("gpa") int gpa, @Param("quiz") int quiz,
                            @Param("level") String level);
    List<Syllabus> findAllByTopicCodeContains(String topicCode);
    List<Syllabus> findByTopicCodeOrTopicNameContainsIgnoreCase(String key, String key1);
    Syllabus findByTopicName(String topicName);
    Syllabus findSyllabusByTopicCode(String topicCode);
    Page<Syllabus> findByTopicCodeOrTopicNameContainsIgnoreCase(String key, String key1, Pageable pageable);
    List<Syllabus> findByCreatedDate(Date date);
    Page<Syllabus> findByCreatedDate(Date date, Pageable pageable);
}
