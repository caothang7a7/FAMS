package com.backend.FAMS.data;/*  Welcome to Jio word
    @author: Jio
    Date: 11/16/2023
    Time: 8:31 AM
    
    ProjectName: fams-backend
    Jio: I wish you always happy with coding <3
*/

import com.backend.FAMS.entity.user.User;
import com.backend.FAMS.entity.user.UserPermission;
import com.backend.FAMS.entity.user.user_enum.Gender;
import com.backend.FAMS.entity.user.user_enum.UserRole;
import com.backend.FAMS.exception.NotFoundException;
import com.backend.FAMS.repository.user_repo.UserPermissionRepository;
import com.backend.FAMS.repository.user_repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserPermissionRepository userPermissionRepository;
    final String password = "123456";

    @Override
    public void run(String... args) throws Exception {


            User admin = new User();
            admin.setEmail("admin@gmail.com");
            UserPermission adminRole = userPermissionRepository.findByRole(UserRole.SUPER_ADMIN)
                    .orElseThrow( () -> new NotFoundException("not find userRole SUPER_ADMIN"));

            admin.setPassword(passwordEncoder.encode(password));
            admin.setUserPermission(adminRole);
            admin.setCreatedDate(new Date());
            admin.setGender(Gender.MALE);
            admin.setDob(new Date("12/12/2002"));
            admin.setPhone("0911111111");
            admin.setName("admin");
            admin.setStatus(true);
            userRepository.save(admin);



    }
}
