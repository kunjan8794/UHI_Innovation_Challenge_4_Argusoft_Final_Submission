package com.argusoft.abdmhackathon.flow.service.impl;

import com.argusoft.abdmhackathon.flow.dao.FlowMasterDao;
import com.argusoft.abdmhackathon.flow.model.FlowMaster;
import com.argusoft.abdmhackathon.flow.service.FlowService;
import com.argusoft.abdmhackathon.question.dao.QuestionMasterDao;
import com.argusoft.abdmhackathon.question.dto.QuestionDto;
import com.argusoft.abdmhackathon.question.mapper.QuestionModelToDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * <p>
 * Add description here
 * </p>
 *
 * @author namrata
 * @since 14/07/2022 4:00 PM
 */
@Service
@Transactional
public class FlowServiceImpl implements FlowService {

    @Autowired
    private QuestionMasterDao questionMasterDao;

    @Autowired
    private FlowMasterDao flowMasterDao;

    @Override
    public QuestionDto getNextQuestion(Integer questionId, String answer, String preferredLanguage) {

        Integer nextQuestionId = null;
        if (Objects.isNull(questionId)) {
            nextQuestionId = flowMasterDao.getFirstQuestionId();
        } else {
            nextQuestionId = flowMasterDao.getFlowByQuestionIDAndAnswer(questionId, answer);
            if (nextQuestionId == null) {
                nextQuestionId = flowMasterDao.getFlowByQuestionIDAndAnswer(questionId, null);
            }
        }
        return QuestionModelToDtoMapper.convertQuestionModelToDto(questionMasterDao.retrieveById(nextQuestionId));
    }
}
