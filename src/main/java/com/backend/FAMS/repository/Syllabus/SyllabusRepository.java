package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOResponse;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tbl_Syllabus (topic_code, topic_name, technical_group, version, training_audience, topic_outline, training_material, training_principal, priority, public_status, created_by, created_date, user_id) " +
            "VALUES (:topicCode, :topicName, :technicalGroup, :version, :trainingAudience,"+
            ":topicOutline, :trainingMaterial, :trainingPrincipal, :priority, :publicStatus,"+
            ":createdBy, :createdDate, :userId)"
            ,nativeQuery = true)
    void customSaveSyllabus(@Param("topicCode") String topicCode, @Param("topicName") String topicName, @Param("technicalGroup") String technicalGroup,
                            @Param("version") String version, @Param("trainingAudience") String trainingAudience, @Param("topicOutline") String topicOutline,
                            @Param("trainingMaterial") String trainingMaterial, @Param("trainingPrincipal") String trainingPrincipal,
                            @Param("priority") String priority, @Param("publicStatus") String publicStatus,
                            @Param("createdBy") String createdBy, @Param("createdDate") Date createdDate,
                            @Param("userId") Long userId);
    List<Syllabus> findAllByTopicCodeContains(String topicCode);

    @Query(value ="Select s.topic_code, s.topic_name, s.public_status, s.created_by, ttc.duration, ttp.training_program_code, s.created_date\n" +
            "From tbl_syllabus s\n" +
            "JOIN tbl_training_unit ttu on s.topic_code = ttu.topic_code\n" +
            "JOIN tbl_training_content ttc on ttu.unit_code = ttc.unit_code\n" +
            "JOIN tbl_training_program ttp on s.user_id = ttp.user_id", nativeQuery = true)
    List<Syllabus> selectSyllabus();
}
