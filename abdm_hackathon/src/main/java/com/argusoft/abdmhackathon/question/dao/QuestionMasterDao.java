package com.argusoft.abdmhackathon.question.dao;

import com.argusoft.abdmhackathon.database.common.GenericDao;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;

/**
 * Defines methods for QuestionMasterDao
 *
 * @author prateek
 * @since 14/07/22 4:17 PM
 */
public interface QuestionMasterDao extends GenericDao<QuestionMaster, Integer> {

    QuestionMaster getNextQuestion(Integer prevQueId, String answer);
}
