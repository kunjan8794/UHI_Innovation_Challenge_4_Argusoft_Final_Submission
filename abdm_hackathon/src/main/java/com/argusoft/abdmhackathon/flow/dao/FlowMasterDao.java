package com.argusoft.abdmhackathon.flow.dao;

import com.argusoft.abdmhackathon.flow.model.FlowMaster;

public interface FlowMasterDao {

    Integer getFlowByQuestionIDAndAnswer(Integer questionId,String answer);

    Integer getFirstQuestionId();
}
