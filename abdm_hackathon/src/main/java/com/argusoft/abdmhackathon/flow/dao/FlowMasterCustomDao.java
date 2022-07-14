package com.argusoft.abdmhackathon.flow.dao;

public interface FlowMasterCustomDao {

    Integer getFlowByQuestionIDAndAnswer(Integer questionId,String answer);

    Integer getFirstQuestionId();
}
