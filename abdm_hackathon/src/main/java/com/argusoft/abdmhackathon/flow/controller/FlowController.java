package com.argusoft.abdmhackathon.flow.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 3:55 PM
 */
@RestController
@RequestMapping("/api/flow")
public class FlowController {

    @GetMapping("next-question")
    public ResponseEntity<Object> getNextQuestion(@RequestParam String questionId,
                                                  @RequestParam String answer) {
        System.out.println("-------");
        return ResponseEntity.ok("Success");
    }
}
