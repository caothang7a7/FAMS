package com.backend.FAMS.dto.home;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountDTO {
    long user;
    long closs;
    long material;
    long training;
}
