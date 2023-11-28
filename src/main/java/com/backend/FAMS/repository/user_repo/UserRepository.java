package com.backend.FAMS.repository.user_repo;


import com.backend.FAMS.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByStatusTrueOrderByName(Pageable pageable);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

//    boolean existsByPassword(String password);
//
//    String findByPassword(String password);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<User> searchByNameOrEmail(@Param("keyword") String keyword);

    long countByStatusTrue();
}
