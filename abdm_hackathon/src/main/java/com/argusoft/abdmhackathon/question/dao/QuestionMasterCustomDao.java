package com.argusoft.abdmhackathon.question.dao;

import com.argusoft.abdmhackathon.question.model.QuestionMaster;

public interface QuestionMasterCustomDao {
    QuestionMaster getNextQuestion(Integer prevQueId, String answer);
}
