package com.argusoft.abdmhackathon.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jay
 */
@RestController
@RequestMapping("/api/")
public class UserController {

    @GetMapping("user")
    public ResponseEntity<Object> getaAllClient() {
        System.out.println("-------");
        return ResponseEntity.ok("Success");
    }
    
    @GetMapping("public")
    public ResponseEntity<Object> accessPublicAPI() {
        System.out.println("-------");
        return ResponseEntity.ok("Success");
    }

}
