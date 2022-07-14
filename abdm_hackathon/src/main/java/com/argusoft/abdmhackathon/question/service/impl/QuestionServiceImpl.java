package com.argusoft.abdmhackathon.question.service.impl;

import com.argusoft.abdmhackathon.question.dao.QuestionMasterDao;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import com.argusoft.abdmhackathon.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMasterDao questionMasterDao;

    @Override
    public QuestionMaster getQuestionByQuestionId(Integer questionId) {
        return questionMasterDao.getById(questionId);
    }
}
