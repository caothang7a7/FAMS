package com.backend.FAMS.entity.Security;

import com.backend.FAMS.entity.User.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "tbl_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    String token;

    Date expiried;

    Boolean active;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    User user;

}
