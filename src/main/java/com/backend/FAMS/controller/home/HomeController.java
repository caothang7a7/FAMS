package com.backend.FAMS.controller.home;

import com.backend.FAMS.service.home.impl.HomeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/home")
public class HomeController {

    @Autowired
    HomeServiceImpl homeServiceImpl;


    @GetMapping("/countTotal")
    public ResponseEntity<?> countTotal() {
        return homeServiceImpl.countTotal();
    }

    @GetMapping("/openClass")
    public ResponseEntity<?> getOpenClass() {
        return homeServiceImpl.openClass();
    }

}
