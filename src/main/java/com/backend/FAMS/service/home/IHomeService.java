package com.backend.FAMS.service.home;

import org.springframework.http.ResponseEntity;


public interface IHomeService {


    ResponseEntity countTotal();

    ResponseEntity openClass();
}
