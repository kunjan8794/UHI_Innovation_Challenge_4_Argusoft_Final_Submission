package com.argusoft.abdmhackathon.flow.service;

import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 4:00 PM
 */
public interface FlowService {

    QuestionDto getNextQuestion(Integer questionId, String answer, String preferredLanguage);
}
