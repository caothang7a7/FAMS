package com.backend.FAMS.repository.User;


import com.backend.FAMS.entity.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findAllByStatusTrueOrderByName(Pageable pageable);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

}
