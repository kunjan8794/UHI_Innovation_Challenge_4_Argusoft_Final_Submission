package com.argusoft.abdmhackathon.question.dao.impl;

import com.argusoft.abdmhackathon.database.dao.GenericRepositoryImpl;
import com.argusoft.abdmhackathon.question.dao.QuestionMasterCustomDao;
import com.argusoft.abdmhackathon.question.model.QuestionMaster;
import com.argusoft.abdmhackathon.question.model.QuestionOptionMaster;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Defines methods for QuestionMasterDaoImpl
 *
 * @author prateek
 * @since 14/07/22 4:29 PM
 */
@Repository
public class QuestionMasterDaoImpl extends GenericRepositoryImpl implements QuestionMasterCustomDao {

    @Override
    public QuestionMaster getNextQuestion(Integer prevQueId, String answer) {
        return null;
    }

    @Override
    public String getQuestionOptionByPreferredLanguage(Integer questionId, String constant, String preferredLanguage) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<QuestionOptionMaster> criteria = criteriaBuilder.createQuery(QuestionOptionMaster.class);
        Root<QuestionOptionMaster> root = criteria.from(QuestionOptionMaster.class);
        Predicate condition = criteriaBuilder.and(criteriaBuilder.equal(
                root.get(QuestionOptionMaster.Fields.QUE_ID), questionId),
                criteriaBuilder.equal(root.get(QuestionOptionMaster.Fields.CONSTANT), constant));
        criteria.select(root).where(condition);
        QuestionOptionMaster  questionOptionMaster = session.createQuery(criteria).uniqueResult();

        if (preferredLanguage.equals("GU")) {
            return questionOptionMaster.getOptionGu();
        } else if (preferredLanguage.equals("HN")) {
            return questionOptionMaster.getOptionHn();
        } else {
            return questionOptionMaster.getOption();
        }
    }
}
