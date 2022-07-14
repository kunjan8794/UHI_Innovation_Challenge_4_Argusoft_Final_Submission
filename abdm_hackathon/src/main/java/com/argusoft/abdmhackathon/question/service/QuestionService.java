package com.argusoft.abdmhackathon.question.service;

import com.argusoft.abdmhackathon.question.model.QuestionMaster;

public interface QuestionService {

    public QuestionMaster getQuestionByQuestionId(Integer questionId);
}
