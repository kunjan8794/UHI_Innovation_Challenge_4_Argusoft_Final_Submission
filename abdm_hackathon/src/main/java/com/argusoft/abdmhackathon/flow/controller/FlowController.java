package com.argusoft.abdmhackathon.flow.controller;

import com.argusoft.abdmhackathon.flow.service.FlowService;
import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private FlowService flowService;

    @GetMapping("next-question")
    public QuestionDto getNextQuestion(@RequestParam Integer questionId,
                                       @RequestParam String answer,
                                       @RequestParam String preferredLanguage) {
        return flowService.getNextQuestion(questionId, answer, preferredLanguage);
    }
}
