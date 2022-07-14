package com.argusoft.abdmhackathon.flow.dao;

import com.argusoft.abdmhackathon.flow.model.FlowMaster;

public interface FlowMasterDao {

    FlowMaster getFlowByQuestionIDAndAnswer(Integer QuestionId,String Answer);
}
