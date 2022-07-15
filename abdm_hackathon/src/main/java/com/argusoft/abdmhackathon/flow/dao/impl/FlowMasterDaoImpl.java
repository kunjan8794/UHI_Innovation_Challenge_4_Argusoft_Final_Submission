package com.argusoft.abdmhackathon.flow.dao.impl;


import com.argusoft.abdmhackathon.database.dao.GenericRepositoryImpl;
import com.argusoft.abdmhackathon.flow.dao.FlowMasterCustomDao;
import com.argusoft.abdmhackathon.flow.model.FlowMaster;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class FlowMasterDaoImpl extends GenericRepositoryImpl implements FlowMasterCustomDao {


    @Override
    public Integer getFirstQuestionId() {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FlowMaster> criteria = criteriaBuilder.createQuery(FlowMaster.class);
        Root<FlowMaster> root = criteria.from(FlowMaster.class);
        Predicate questionCondition = criteriaBuilder.isTrue(root.get(FlowMaster.Fields.IS_FIRST_QUESTION));
        criteria.select(root).where(questionCondition);
        List<FlowMaster> flowMasters = (List<FlowMaster>) session.createQuery(criteria).list();
        return flowMasters.get(0).getQuestionId();
    }

    @Override
    public Integer getFlowByQuestionIDAndAnswer(Integer questionId, String answer) {
        Session session = getSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<FlowMaster> criteria = criteriaBuilder.createQuery(FlowMaster.class);
        Root<FlowMaster> root = criteria.from(FlowMaster.class);
        Predicate questionCondition = null;

        if (questionId != null && answer != null) {
            // if questionId is there and answer is not null
            questionCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.QUESTION_ID), questionId);
            Predicate answerCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.ANSWER), answer);
            criteria.select(root).where(criteriaBuilder.and(questionCondition, answerCondition));
            List<FlowMaster> flowMaster = session.createQuery(criteria).list();
            if (CollectionUtils.isEmpty(flowMaster))
                return null;
            return flowMaster.get(0).getNextQuestionId();
        } else {
            questionCondition = criteriaBuilder.equal(root.get(FlowMaster.Fields.QUESTION_ID), questionId);
        }
        criteria.select(root).where(questionCondition);
        FlowMaster flowMaster = (FlowMaster) session.createQuery(criteria).uniqueResult();
        return flowMaster.getNextQuestionId();
    }

}
