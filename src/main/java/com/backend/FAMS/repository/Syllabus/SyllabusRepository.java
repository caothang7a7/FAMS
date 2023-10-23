package com.backend.FAMS.repository.Syllabus;

import com.backend.FAMS.dto.Syllabus.response.SyllabusDTOShowOtherScreen;
import com.backend.FAMS.entity.Syllabus.Syllabus;
import com.backend.FAMS.entity.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, String> {
//    @Query()
//    Syllabus createOtherScreen(int quiz,
//                               int assignment,
//                               int finalTest,
//                               int finalTheory,
//                               int finalPractice,
//                               int gpa,
//                               String TrainingPrincipal,
//                               User user
//
//    );

    Syllabus findSyllabusByTopicName(String topicName);

}
