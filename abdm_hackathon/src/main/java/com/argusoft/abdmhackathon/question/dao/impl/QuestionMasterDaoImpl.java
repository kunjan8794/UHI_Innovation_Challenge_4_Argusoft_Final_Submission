package com.argusoft.abdmhackathon.question.dao.impl;

import com.argusoft.abdmhackathon.database.common.impl.GenericDaoImpl;
import com.argusoft.abdmhackathon.question.dao.QuestionMasterDao;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import org.springframework.stereotype.Repository;

/**
 * Defines methods for QuestionMasterDaoImpl
 *
 * @author prateek
 * @since 14/07/22 4:29 PM
 */
@Repository
public class QuestionMasterDaoImpl extends GenericDaoImpl<QuestionMaster, Integer> implements QuestionMasterDao {

    @Override
    public QuestionMaster getNextQuestion(Integer prevQueId, String answer) {
        return null;
    }
}
