package com.backend.FAMS;

import com.backend.FAMS.entity.User.UserPermission;
import com.backend.FAMS.entity.User.user_enum.UserRole;
import com.backend.FAMS.repository.User.UserPermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class FamsApplication {



	public static void main(String[] args) {
		SpringApplication.run(FamsApplication.class, args);
	}

}
